package com.example.spring.config;

import com.example.bl.account.AccountService;
import com.example.bl.session.SessionService;
import com.example.spring.rest.AccountController;
import com.example.spring.rest.SessionController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestConfig {

    @Bean
    public AccountController accountController(AccountService accountService) {
        return new AccountController(accountService);
    }

    @Bean
    public SessionController sessionController(SessionService sessionService) {
        return new SessionController(sessionService);
    }
}
