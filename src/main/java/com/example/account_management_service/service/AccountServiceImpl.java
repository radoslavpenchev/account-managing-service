package com.example.account_management_service.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.account_management_service.model.Account;
import com.example.account_management_service.repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account createAccount(Account account) {
        account.setRegisteredAt(LocalDateTime.now());
        account.setMarkedForDeletion(false);
        return accountRepository.save(account);
    }

    @Override
    public Account updateAccount(UUID id, Account updatedAccount) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        account.setEmail(updatedAccount.getEmail());
        account.setPassword(updatedAccount.getPassword());
        return accountRepository.save(account);
    }

    @Override
    public void markAccountForDeletion(UUID id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        account.setMarkedForDeletion(true);
        accountRepository.save(account);
    }

    @Override
    public Account getAccountById(UUID id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }

    @Override
    public List<Account> getActiveAccounts() {
        return accountRepository.findByMarkedForDeletionFalse();
    }
}
