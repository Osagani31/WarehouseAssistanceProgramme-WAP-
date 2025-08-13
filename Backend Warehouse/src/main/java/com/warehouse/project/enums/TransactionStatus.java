package com.warehouse.project.enums;

public enum TransactionStatus {
    PENDING,           // created but not acted on
    ACCEPTED,          // Supplier accepts the order
    REJECTED,          // Supplier rejects the order
    PROCESSING,        // Supplier is preparing shipment
    SHIPPED,           // Supplier shipped the order
    RECEIVED,          // Receiving Manager received it
    RETURNED,          // Items returned (e.g., damaged)
    COMPLETED,         // Process finalized
    CANCELLED          // Cancelled by manager
}
