package com.wlo.kafka.consumer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wlo.kafka.consumer.dto.TransactionEventDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
public class KafkaConsumerService {

    private final ObjectMapper objectMapper;

    public KafkaConsumerService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;;
    }


    @KafkaListener(topics = "kafka-events" , containerFactory = "kafkaListenerContainerFactory", groupId = "kafka.consumer")
    public void consumeTransactionEvent(byte[] event) {
        try {
            TransactionEventDTO transactionEvent = objectMapper.readValue(event, TransactionEventDTO.class);
        } catch (JsonProcessingException e) {
            log.error("Error mapping object");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
