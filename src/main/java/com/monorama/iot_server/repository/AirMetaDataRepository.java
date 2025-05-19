package com.monorama.iot_server.repository;

import com.monorama.iot_server.domain.AirMetaData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AirMetaDataRepository extends JpaRepository<AirMetaData, Long> {

    @Query("""
    SELECT a FROM AirMetaData a
    WHERE a.project.id = :projectId
    """)
    List<AirMetaData> findAllByProjectId(Long projectId);

}
