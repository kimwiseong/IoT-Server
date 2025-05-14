package com.monorama.iot_server.domain;

import com.monorama.iot_server.domain.embedded.HealthDataItem;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name = "health_data_tb")
public class HealthData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "health_data_id")
    private Long id;

    /*** basic information ***/
    @CreationTimestamp
    @Column(name = "created_at", columnDefinition = "DATETIME(0)")
    private Date createdAt;

    @Embedded
    private HealthDataItem healthDataItem;

    /*** mapping information ***/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    /*** business logic ***/
    private void setUser(User user) {
        this.user = user;
        user.getHealthDataList().add(this);
    }
}
