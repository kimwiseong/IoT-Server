package com.monorama.iot_server.repository;

import com.monorama.iot_server.domain.UserDataPermission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDataPermissonRepository extends JpaRepository<UserDataPermission, Long> {

}
