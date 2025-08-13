package com.warehouse.project.models;

import com.warehouse.project.enums.DispatchStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="dispatch_products")
@Data
@Builder
public class DispatchAction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime shippedAt;

    private String carrierName;
    private String trackingNumber;

    @Enumerated(EnumType.STRING)
    private DispatchStatus dispatchStatus;

    @ManyToOne
    private User dispatchManager;

    @OneToOne
    private Transactions transaction;
}
