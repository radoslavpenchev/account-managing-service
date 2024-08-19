package com.example.rest.dto.session;

import com.example.rest.dto.AbstractEntityDto;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class SessionDto extends AbstractEntityDto {

    @JsonProperty("accountId")
    private UUID accountId;

    @JsonProperty("ipAddress")
    private String ipAddress;

    public SessionDto() {
    }

    public SessionDto(UUID id) {
        super(id);
    }

    public UUID getAccountId() {
        return accountId;
    }

    public void setAccountId(UUID accountId) {
        this.accountId = accountId;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}
