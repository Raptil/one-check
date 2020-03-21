package org.startup.onecheck.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.startup.onecheck.model.entity.Check;

public interface CheckRepository extends JpaRepository<Check, Long> {
}
