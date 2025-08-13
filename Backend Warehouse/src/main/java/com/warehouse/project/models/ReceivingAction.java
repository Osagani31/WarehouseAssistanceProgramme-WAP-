package com.warehouse.project.models;

import com.warehouse.project.enums.ReceivingStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="receive_products")
@Data
@Builder
public class ReceivingAction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int receivedQuantity;

    private int returnedQuantity;

    private String returnReason;

    @Enumerated(EnumType.STRING)
    private ReceivingStatus status;

    @ManyToOne
    private User receivingManager;

    private LocalDateTime actionDate;

    @OneToOne
    private Transactions transaction;
}
