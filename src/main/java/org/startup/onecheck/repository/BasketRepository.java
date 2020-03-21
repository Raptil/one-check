package org.startup.onecheck.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.startup.onecheck.model.entity.Basket;

public interface BasketRepository extends JpaRepository<Basket, Long> {



}
