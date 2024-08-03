package com.wlo.kafka.consumer.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@Table(name = "transaction_log")
@Entity
@ToString
public class TransactionEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Event ID cannot be blank")
    private String eventId;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "Date cannot be null")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}$", message = "Date must be in the format yyyy-MM-dd HH:mm:ss")
    private String date;

    @NotBlank(message = "Operation Code cannot be blank")
    private String operationId;

    @NotBlank(message = "Operation Activity cannot be blank")
    private String operationCode;

    @NotBlank(message = "Service Name cannot be blank")
    private String serviceName;

    @NotBlank(message = "Channel cannot be blank")
    private String channel;

    @NotBlank(message = "Version cannot be blank")
    private String version;


    // add more parameters for logging
}

