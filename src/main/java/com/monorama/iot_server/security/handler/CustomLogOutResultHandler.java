package com.monorama.iot_server.security.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.minidev.json.JSONValue;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class CustomLogOutResultHandler implements LogoutSuccessHandler  {
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        setSuccessResponse(response);
    }
    private void setSuccessResponse(HttpServletResponse response) throws IOException {
        Map<String, Object> result = new HashMap<>();
        result.put("success", Boolean.TRUE);
        result.put("data","logout success");
        result.put("error", null);
        response.getWriter().write(JSONValue.toJSONString(result));
    }
//    private void setFailureResponse(HttpServletResponse response) throws IOException {
//        setErrorResponse(response,ErrorCode.FAILURE_LOGIN);
//    }
}
