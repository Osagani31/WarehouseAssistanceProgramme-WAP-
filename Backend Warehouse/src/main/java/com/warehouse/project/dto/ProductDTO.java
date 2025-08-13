package com.warehouse.project.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class ProductDTO {

    private Long id;

    private Long categoryId;
    private Long productId;
    private Long supplierId;

    private String name;

    private String sku;

    private Double price;

    private Integer stockQuantity;

    private String description;
    private LocalDateTime warrantyEndDate;
    private String imageUrl;

    private LocalDateTime createdAt;
}
