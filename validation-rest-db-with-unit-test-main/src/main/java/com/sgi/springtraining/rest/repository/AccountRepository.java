package com.sgi.springtraining.rest.repository;

import com.sgi.springtraining.rest.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}