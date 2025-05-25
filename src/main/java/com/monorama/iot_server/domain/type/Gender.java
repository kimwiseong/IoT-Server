package com.monorama.iot_server.domain.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.monorama.iot_server.exception.CommonException;
import com.monorama.iot_server.exception.ErrorCode;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Gender {
    MALE("Male"), FEMALE("Female"), OTHER("Other");

    private final String gender;

    @Override
    public String toString() {
        return gender;
    }

    @JsonCreator
    public static Gender from(String gender) {
        for (Gender g : values()) {
            if (g.gender.equals(gender))
                return g;
        }
        throw new CommonException(ErrorCode.UNKNOWN_GENDER_ERROR);
    }
}
