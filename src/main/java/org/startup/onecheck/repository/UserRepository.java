package org.startup.onecheck.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.startup.onecheck.model.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
