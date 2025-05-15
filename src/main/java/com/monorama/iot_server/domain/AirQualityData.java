package com.monorama.iot_server.domain;

import com.monorama.iot_server.domain.embedded.AirQualityDataItem;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "air_quality_data_tb")
public class AirQualityData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "air_quality_data_id")
    private Long id;

    /*** basic information ***/
    @Column(name = "created_at", columnDefinition = "DATETIME(0)", nullable = false)
    private Date createdAt;

    @Embedded
    private AirQualityDataItem airQualityDataItem;

    /*** mapping information ***/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    /*** business logic ***/
    public void setUser(User user) {
        this.user = user;
        user.getAirQualityDataList().add(this);
    }

    public void setAirQualityDataItem(AirQualityDataItem item) {
        this.airQualityDataItem = item;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
