package com.wlo.kafka.consumer.dto;

import lombok.*;

import java.io.Serializable;

@Builder
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionEventDTO implements Serializable {

    private String eventId;
    private String channel;
    private String version;

    // add more parameters
}
