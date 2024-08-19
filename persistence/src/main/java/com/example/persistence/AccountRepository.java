package com.example.persistence;

import java.util.List;
import java.util.UUID;

import com.example.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, UUID> {
    List<Account> findByMarkedForDeletionFalse();
}
