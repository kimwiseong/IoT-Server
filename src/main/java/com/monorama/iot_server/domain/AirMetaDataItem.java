package com.monorama.iot_server.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "air_meta_data_item_tb")
public class AirMetaDataItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "air_meta_data_item_id")
    private Long id;

    /*** basic information ***/
    @Column(name = "data_name")
    private String dataName;

    @Column(name = "data_type")
    private String dataType;

    /*** mapping information ***/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    @OneToMany(mappedBy = "airMetaDataItem")
    private List<AirMetaData> airMetaDataList = new ArrayList<>();

    /*** business logic ***/
    private void setProject(Project project) {
        this.project = project;
        project.getAirMetaDataItemList().add(this);
    }
}
