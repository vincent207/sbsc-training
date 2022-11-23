package com.sgi.springtraining.rest.repository;

import com.sgi.springtraining.rest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}