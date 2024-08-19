package com.example.bl.transformer.account;

import com.example.bl.transformer.AbstractEntityTransformer;
import com.example.domain.Account;
import com.example.rest.dto.account.AccountDto;

public class AccountTransformer extends AbstractEntityTransformer<Account, AccountDto> {

    public static final AccountTransformer instance = new AccountTransformer();

    private AccountTransformer() {
        // Singleton
    }

    @Override
    public void copyToTarget(Account account, AccountDto accountDto) {
        super.copyToTarget(account, accountDto);

        accountDto.setEmail(account.getEmail());
        accountDto.setPassword(account.getPassword());
        accountDto.setRegisteredAt(account.getRegisteredAt());
        accountDto.setMarkedForDeletion(account.isMarkedForDeletion());
    }

    @Override
    public void copyToSource(AccountDto accountDto, Account account) {
        super.copyToSource(accountDto, account);

        account.setEmail(accountDto.getEmail());
        account.setPassword(accountDto.getPassword());
        account.setRegisteredAt(accountDto.getRegisteredAt());
        account.setMarkedForDeletion(account.isMarkedForDeletion());
    }

    @Override
    public AccountDto createTarget(Account account) {
        if (account == null) {
            return null;
        }

        final AccountDto dto = new AccountDto();
        copyToTarget(account, dto);
        return dto;
    }

    @Override
    public Account createSource(AccountDto accountDto) {
        if (accountDto == null) {
            return null;
        }

        final Account account = new Account();
        copyToSource(accountDto, account);
        return account;
    }
}
