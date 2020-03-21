package org.startup.onecheck.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.startup.onecheck.model.entity.Check;
import org.startup.onecheck.model.entity.User;

public interface CheckRepository extends JpaRepository<Check, Long> {

    public Check findByUserEquals(User user);
}
