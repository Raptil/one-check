package org.startup.onecheck.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.startup.onecheck.model.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
