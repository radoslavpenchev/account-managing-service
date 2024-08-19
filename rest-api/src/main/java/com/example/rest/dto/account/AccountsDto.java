package com.example.rest.dto.account;

import com.example.rest.dto.AbstractDto;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class AccountsDto extends AbstractDto {

    @JsonProperty("accounts")
    private List<AccountDto> accounts = new ArrayList<>();

    public AccountsDto() {
        // POJO
    }

    public AccountsDto(List<AccountDto> accounts) {
        this.accounts = accounts;
    }

    public List<AccountDto> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AccountDto> accounts) {
        this.accounts = accounts;
    }
}
