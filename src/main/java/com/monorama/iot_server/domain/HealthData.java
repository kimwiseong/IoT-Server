package com.monorama.iot_server.domain;

import com.monorama.iot_server.domain.embedded.HealthDataItem;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

import static jakarta.persistence.FetchType.*;
import static jakarta.persistence.GenerationType.*;

@Entity(name = "health_data_tb")
public class HealthData {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "health_data_id")
    private final Long id = 0L;

    @CreationTimestamp
    @Column(name = "created_at")
    private Date createdAt;

    @Embedded
    private HealthDataItem healthDataItem;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private void setUser(User user) {
        this.user = user;
        user.getHealthDataList().add(this);
    }

}
