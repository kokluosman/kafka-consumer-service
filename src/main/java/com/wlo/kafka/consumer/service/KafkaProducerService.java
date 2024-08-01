package com.wlo.kafka.consumer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wlo.kafka.consumer.dto.TransactionEventDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class KafkaProducerService {

    private final KafkaTemplate<String, byte[]> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public KafkaProducerService(KafkaTemplate<String, byte[]> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    @Scheduled(fixedRate = 1000) // Send 10 events per second
    public void sendMessage() {
        String topic = "kafka-events";
        String key = "wlo";
        TransactionEventDTO value = TransactionEventDTO.builder()
                .eventId("wlo.event")
                .version("19")
                .channel("WEB")
                .build();
        try {
            byte[] message = objectMapper.writeValueAsBytes(value);
            kafkaTemplate.send(topic, key, message);
        } catch (JsonProcessingException e) {
            log.error("error while converting to byte");
        }
    }
}
