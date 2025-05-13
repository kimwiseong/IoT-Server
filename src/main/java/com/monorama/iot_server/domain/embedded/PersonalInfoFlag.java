package com.monorama.iot_server.domain.embedded;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class PersonalInfoFlag {

    @Column(name = "name_flag", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean name;

    @Column(name = "email_flag", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean email;

    @Column(name = "gender_flag", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean gender;

    @Column(name = "national_code_flag", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean nationalCode;

    @Column(name = "phone_number_flag", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean phoneNumber;

    @Column(name = "date_of_birth_flag", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean dateOfBirth;

    @Column(name = "blood_type_flag", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean bloodType;

    @Column(name = "height_flag", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean height;

    @Column(name = "weight_flag", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean weight;
}


