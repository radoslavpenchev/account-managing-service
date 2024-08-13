package com.example.account_management_service.service;


import java.util.List;
import java.util.UUID;

import com.example.account_management_service.model.Account;

public interface AccountService {
    Account createAccount(Account account);
    Account updateAccount(UUID id, Account account);
    void markAccountForDeletion(UUID id);
    Account getAccountById(UUID id);
    List<Account> getActiveAccounts();
}
