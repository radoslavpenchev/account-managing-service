package com.example.spring.config;

import com.example.bl.account.AccountService;
import com.example.bl.session.SessionService;
import com.example.persistence.AccountRepository;
import com.example.persistence.SessionRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    public AccountService accountService(AccountRepository accountRepository) {
        return new AccountService(accountRepository);
    }

    @Bean
    public SessionService sessionService(SessionRepository sessionRepository) {
        return new SessionService(sessionRepository);
    }
}
