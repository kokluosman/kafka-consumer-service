package com.wlo.kafka.consumer.dao;

import com.wlo.kafka.consumer.model.TransactionEvent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionEventRepository extends CrudRepository<TransactionEvent, String> {
}
