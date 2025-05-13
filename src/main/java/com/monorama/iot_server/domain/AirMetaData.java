package com.monorama.iot_server.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "air_mata_data_tb")
public class AirMetaData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "air_meta_data_id")
    private Long id;

    /*** basic information ***/
    @Column(name = "meta_data_value")
    private String metaDataValue;

    /*** mapping information ***/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "air_meta_data_item_id")
    private AirMetaDataItem airMetaDataItem;

    /*** business logic ***/
    private void setUser(User user) {
        this.user = user;
        user.getAirMetaDataList().add(this);
    }

    private void setProject(Project project) {
        this.project = project;
        project.getAirMetaDataList().add(this);
    }

    private void setAirMetaDataItem(AirMetaDataItem airMetaDataItem) {
        this.airMetaDataItem = airMetaDataItem;
        airMetaDataItem.getAirMetaDataList().add(this);
    }
}
