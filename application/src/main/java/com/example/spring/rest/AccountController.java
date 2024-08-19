package com.example.spring.rest;

import com.example.bl.account.AccountService;
import com.example.rest.api.AccountsRestService;
import com.example.rest.dto.account.AccountDto;
import com.example.rest.dto.account.AccountsDto;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/accounts")
public class AccountController implements AccountsRestService {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    @Override
    public AccountDto createAccount(AccountDto account) {
        return accountService.createAccount(account);
    }

    @PutMapping("/{id}")
    @Override
    public AccountDto updateAccount(UUID id, AccountDto account) {
        return accountService.updateAccount(id, account);
    }

    @DeleteMapping("/{id}")
    @Override
    public void markAccountForDeletion(@PathVariable UUID id) {
        accountService.markAccountForDeletion(id);
    }

    @GetMapping("/{id}")
    @Override
    public AccountDto getAccountById(@PathVariable UUID id) {
        return accountService.getAccountById(id);
    }

    @GetMapping("/active")
    @Override
    public AccountsDto getActiveAccounts() {
        return accountService.getActiveAccounts();
    }
}
