package com.wlo.kafka.consumer.service;

import com.wlo.kafka.consumer.dao.TransactionEventRepository;
import com.wlo.kafka.consumer.dto.TransactionEventDTO;
import com.wlo.kafka.consumer.model.EventParams;
import com.wlo.kafka.consumer.model.TransactionEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class TransactionEventService {

    private static final Logger log = LoggerFactory.getLogger(TransactionEventService.class);
    private final TransactionEventRepository transactionEventRepository;

    public TransactionEventService(TransactionEventRepository transactionEventRepository) {
        this.transactionEventRepository = transactionEventRepository;
    }

    public void sendEventToDb(EventParams eventParams, TransactionEventDTO transactionEventDTO) {
        TransactionEvent transactionEvent = TransactionEvent.builder()
                .eventId(transactionEventDTO.getEventId())
                .date(getFormattedDate())
                .channel(transactionEventDTO.getChannel())
                .version(transactionEventDTO.getVersion())
                .operationCode(eventParams.getOperationCode())
                .serviceName(eventParams.getServiceName())
                .operationId(eventParams.getOperationId())
                .build();

        try {
            TransactionEvent save = transactionEventRepository.save(transactionEvent);
            log.info(String.valueOf(save));
        } catch (Exception e) {
            log.error("Error while sending to database");
        }
    }

    private String getFormattedDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.now().format(formatter);
    }
}
