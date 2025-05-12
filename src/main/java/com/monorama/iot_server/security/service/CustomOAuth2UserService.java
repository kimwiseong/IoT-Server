package com.monorama.iot_server.security.service;

import com.monorama.iot_server.domain.User;
import com.monorama.iot_server.repository.UserRepository;
import com.monorama.iot_server.security.info.OAuth2UserInfo;
import com.monorama.iot_server.security.info.OAuth2UserInfoFactory;
import com.monorama.iot_server.security.info.UserPrincipal;
import com.monorama.iot_server.type.EProvider;
import com.monorama.iot_server.type.EProviderFactory;
import com.monorama.iot_server.type.ERole;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Locale;

@RequiredArgsConstructor
@Service
@Slf4j
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    private final UserRepository userRepository;
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
         try {
             return process(userRequest, super.loadUser(userRequest));
        }catch (Exception ex){
             ex.printStackTrace();
             throw ex;
         }
    }

    public OAuth2User process(OAuth2UserRequest userRequest, OAuth2User oAuth2User){
        EProvider provider = EProviderFactory.of(userRequest.getClientRegistration().getRegistrationId().toUpperCase(Locale.ROOT));
        OAuth2UserInfo userInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(provider,oAuth2User.getAttributes());

        UserRepository.UserSecurityForm userSecurityForm = userRepository.findBySocialIdAndEProvider(userInfo.getId(),provider)
                .orElseGet(()->
                {
                    User user = userRepository.save(
                            User.builder()
                                    .role(ERole.GUEST)
                                    .socialId(userInfo.getId())
                                    .provider(provider)
                                    .build()
                    );
                    return UserRepository.UserSecurityForm.invoke(user);
                });

        return UserPrincipal.create(userSecurityForm,oAuth2User.getAttributes());
    }
}
