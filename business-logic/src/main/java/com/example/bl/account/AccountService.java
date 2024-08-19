package com.example.bl.account;

import com.example.bl.transformer.account.AccountTransformer;
import com.example.domain.Account;
import com.example.persistence.AccountRepository;
import com.example.rest.dto.account.AccountDto;
import com.example.rest.dto.account.AccountsDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public AccountDto createAccount(AccountDto account) {
        if (account.getId() != null) {
            throw new IllegalArgumentException("Account already exist!");   // This should be conflict exception
        }

        final Account newAccount = AccountTransformer.instance.createSource(account);
        newAccount.setRegisteredAt(LocalDateTime.now());
        final Account savedAccount = accountRepository.save(newAccount);
        return AccountTransformer.instance.createTarget(savedAccount);
    }

    public AccountDto updateAccount(UUID id, AccountDto accountDto) {
        final Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
        AccountTransformer.instance.copyToSource(accountDto, account);
        final Account updatedAccount = accountRepository.save(account);
        return AccountTransformer.instance.createTarget(updatedAccount);
    }

    public void markAccountForDeletion(UUID id) {
        final Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
        account.setMarkedForDeletion(true);
        accountRepository.save(account);
    }

    public AccountDto getAccountById(UUID id) {
        final Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
        return AccountTransformer.instance.createTarget(account);
    }

    public AccountsDto getActiveAccounts() {
        final List<Account> accounts = accountRepository.findByMarkedForDeletionFalse();
        final List<AccountDto> accountDtos = accounts.stream().map(AccountTransformer.instance::createTarget).toList();
        return new AccountsDto(accountDtos);
    }
}
