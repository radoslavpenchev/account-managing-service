package com.example.spring;

import com.example.spring.config.RestConfig;
import com.example.spring.config.ServiceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.example.domain")
@EnableJpaRepositories("com.example.persistence")
@ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX, pattern = "com.example.spring.*"))
@Import({
        ServiceConfig.class,
        RestConfig.class
})
public class AccountManagementServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(AccountManagementServiceApplication.class, args);
    }
}
