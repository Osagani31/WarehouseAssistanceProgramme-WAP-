package com.warehouse.project.repositories;

import com.warehouse.project.models.Products;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Products,Long> {

    List<Products> findByNameContainingAndDescriptionContaining(String name, String description);

}
