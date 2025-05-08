package com.monorama.iot_server.domain;

import com.monorama.iot_server.domain.embedded.AirQualityDataItem;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

import static jakarta.persistence.FetchType.LAZY;

@Entity(name = "air_quality_data_tb")
public class AirQualityData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "air_quality_data_id")
    private final Long id = 0L;

    @CreationTimestamp
    @Column(name="created_at")
    private Date createdAt;

    @Embedded
    private AirQualityDataItem airQualityDataItem;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private void setUser(User user) {
        this.user = user;
        user.getAirQualityDataList().add(this);
    }
}
