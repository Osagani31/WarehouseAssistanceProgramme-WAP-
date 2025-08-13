package com.warehouse.project.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="categories")
@Data
@Builder
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    private boolean status;

    @OneToMany(mappedBy ="category",cascade = CascadeType.ALL)
    private List<Products> products;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<StorageLocation> storageLocations;

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", products=" + products +
                ", storageLocation=" + storageLocations +
                '}';
    }
}
