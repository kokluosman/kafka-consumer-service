package com.wlo.kafka.consumer.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@Table(name = "event_params")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class EventParams {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String eventId;

    private String operationCode;

    private String operationId;

    private String serviceName;
}
