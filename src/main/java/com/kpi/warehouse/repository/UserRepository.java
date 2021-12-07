package com.kpi.warehouse.repository;

import com.kpi.warehouse.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
