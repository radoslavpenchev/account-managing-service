package com.example.account_management_service.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.account_management_service.model.Account;

public interface AccountRepository extends JpaRepository<Account, UUID> {
    List<Account> findByMarkedForDeletionFalse();
}
