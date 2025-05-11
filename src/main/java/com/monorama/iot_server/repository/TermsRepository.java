package com.monorama.iot_server.repository;

import com.monorama.iot_server.domain.Terms;
import com.monorama.iot_server.domain.type.TermsType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TermsRepository extends JpaRepository<Terms, Long> {
    List<Terms> findByTypeIn(List<TermsType> types);
}
