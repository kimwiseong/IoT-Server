package com.monorama.iot_server.dto.request;

import com.monorama.iot_server.domain.embedded.PersonalInfoItem;
import com.monorama.iot_server.domain.type.BloodType;
import com.monorama.iot_server.domain.type.Gender;
import com.monorama.iot_server.domain.type.NationalCode;

import java.util.Date;

public record PMRegisterDto(
        String name,
        String email,
        Gender gender,
        NationalCode nationalCode,
        String phoneNumber,
        Date dateOfBirth,
        BloodType bloodType,
        Double height,
        Double weight
) {
    public PersonalInfoItem toEntity() {
        return new PersonalInfoItem(
                name,
                email,
                gender,
                nationalCode,
                phoneNumber,
                dateOfBirth,
                bloodType,
                height,
                weight
        );
    }
}
