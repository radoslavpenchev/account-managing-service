package com.example.rest.api;

import com.example.rest.dto.account.AccountDto;
import com.example.rest.dto.account.AccountsDto;

import java.util.UUID;

public interface AccountsRestService {

    AccountDto createAccount(AccountDto account);

    AccountDto updateAccount(UUID id, AccountDto account);

    void markAccountForDeletion(UUID id);

    AccountDto getAccountById(UUID id);

    AccountsDto getActiveAccounts();

}
