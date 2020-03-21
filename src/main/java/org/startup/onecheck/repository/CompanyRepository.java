package org.startup.onecheck.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.startup.onecheck.model.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
