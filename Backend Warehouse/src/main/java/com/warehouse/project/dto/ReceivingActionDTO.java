package com.warehouse.project.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.warehouse.project.enums.ReceivingStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReceivingActionDTO {
    private Long id;

    private Integer receivedQuantity;

    private Integer returnedQuantity;

    private String returnReason;

    private ReceivingStatus status;

    private LocalDateTime actionDate;

    private UserDTO receivingManager;

    private TransactionDTO transaction;
}
