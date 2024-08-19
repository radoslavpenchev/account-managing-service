package com.example.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "sessions")
public class Session extends AbstractEntity {

    @Column(nullable = false)
    private String ipAddress;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    public Session() {
        // POJO
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
