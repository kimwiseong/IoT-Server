package com.monorama.iot_server.domain.embedded;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PersonalInfoFlag {

    @Column(name = "name")
    private Boolean name;

    @Column(name = "email")
    private Boolean email;

    @Column(name = "gender")
    private Boolean gender;

    @Column(name = "national_code")
    private Boolean nationalCode;

    @Column(name = "phone_number")
    private Boolean phoneNumber;

    @Column(name = "date_of_birth")
    private Boolean dateOfBirth;

    @Column(name = "blood_type")
    private Boolean bloodType;

    @Column(name = "height")
    private Boolean height;

    @Column(name = "weight")
    private Boolean weight;
}

