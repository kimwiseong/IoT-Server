package com.monorama.iot_server.domain.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.monorama.iot_server.exception.CommonException;
import com.monorama.iot_server.exception.ErrorCode;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum BloodType {
    A_PLUS("A+"), A_MINUS("A-"), B_PLUS("B+"), B_MINUS("B-"),
    O_PLUS("O+"), O_MINUS("O-"), AB_PLUS("AB+"), AB_MINUS("AB-"), UNKNOWN("Unknown");

    private final String bloodType;

    @Override
    public String toString() {
        return bloodType;
    }

    @JsonCreator
    public static BloodType from(String bloodType) {
        for (BloodType bt : values()) {
            if (bt.bloodType.equals(bloodType))
                return bt;
        }
        throw new CommonException(ErrorCode.UNKNOWN_BLOOD_TYPE_ERROR);
    }

}
