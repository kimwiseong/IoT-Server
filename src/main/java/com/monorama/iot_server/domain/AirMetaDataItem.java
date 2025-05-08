package com.monorama.iot_server.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Entity(name = "air_meta_data_item_tb")
@Getter
public class AirMetaDataItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "air_meta_data_item_id")
    private final Long id = 0L;

    @Column(name = "data_name")
    private String dataName;

    @Column(name = "data_type")
    private String dataType;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    @OneToMany(mappedBy = "airMetaDataItem")
    private List<AirMetaData> airMetaDataList = new ArrayList<>();

    private void setProject(Project project) {
        this.project = project;
        project.getAirMetaDataItemList().add(this);
    }

}
