package com.monorama.iot_server.domain.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.monorama.iot_server.exception.CommonException;
import com.monorama.iot_server.exception.ErrorCode;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum NationalCode {
    US("+1"),
    KR("+82");

    private final String nationalCode;

    @Override
    public String toString() {
        return nationalCode;
    }

    @JsonCreator
    public static NationalCode from(String nationalCode) {
        for (NationalCode nc : values()) {
            if (nc.nationalCode.equals(nationalCode))
                return nc;
        }
        throw new CommonException(ErrorCode.UNKNOWN_NATIONAL_CODE_ERROR);
    }

}
