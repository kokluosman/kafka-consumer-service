package com.wlo.kafka.consumer.dao;

import com.wlo.kafka.consumer.model.EventParams;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventParamsRepository extends JpaRepository<EventParams,Long> {
}
