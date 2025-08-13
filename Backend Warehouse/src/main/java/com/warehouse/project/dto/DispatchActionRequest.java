package com.warehouse.project.dto;

import com.warehouse.project.enums.DispatchStatus;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DispatchActionRequest {

    @Positive(message = "Transaction ID is required")
    private Long transactionId;

    private String carrierName;

    private String trackingNumber;

    private DispatchStatus dispatchStatus;

    private String notes;
}
