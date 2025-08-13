package com.warehouse.project.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.warehouse.project.enums.DispatchStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DispatchActionDTO {


    private Long id;

    private LocalDateTime shippedAt;

    private String carrierName;

    private String trackingNumber;

    private DispatchStatus dispatchStatus;

    private UserDTO dispatchManager;

    private TransactionDTO transaction;

    private String notes;
}
