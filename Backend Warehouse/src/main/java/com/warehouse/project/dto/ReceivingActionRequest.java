package com.warehouse.project.dto;

import com.warehouse.project.enums.ReceivingStatus;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceivingActionRequest {
    @Positive(message = "Transaction ID is required")
    private Long transactionId;

    private Integer receivedQuantity;

    private Integer returnedQuantity;

    private String returnReason;

    private ReceivingStatus status;
}
