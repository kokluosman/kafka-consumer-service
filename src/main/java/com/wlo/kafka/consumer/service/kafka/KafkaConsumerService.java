package com.wlo.kafka.consumer.service.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wlo.kafka.consumer.dto.TransactionEventDTO;
import com.wlo.kafka.consumer.service.LocalCacheService;
import com.wlo.kafka.consumer.service.TransactionEventService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
public class KafkaConsumerService {

    private final ObjectMapper objectMapper;
    private final LocalCacheService localCacheService;
    private final TransactionEventService eventService;

    public KafkaConsumerService(ObjectMapper objectMapper, LocalCacheService localCacheService, TransactionEventService eventService) {
        this.objectMapper = objectMapper;
        this.localCacheService = localCacheService;
        this.eventService = eventService;
    }


    @KafkaListener(topics = "kafka-events" , containerFactory = "kafkaListenerContainerFactory", groupId = "kafka.consumer")
    public void consumeTransactionEvent(byte[] event) {
        try {
            TransactionEventDTO transactionEvent = objectMapper.readValue(event, TransactionEventDTO.class);

            localCacheService.getAllCache().stream()
                    .filter(eventParams -> transactionEvent.getEventId().equals(eventParams.getEventId()))
                    .findFirst()
                    .ifPresent(eventParams -> eventService.sendEventToDb(eventParams,transactionEvent));

        } catch (JsonProcessingException e) {
            log.error("Error mapping object");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
