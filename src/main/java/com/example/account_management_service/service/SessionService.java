package com.example.account_management_service.service;


import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.account_management_service.model.Session;

public interface SessionService {
    Session createSession(Session session);
    void deleteSession(UUID id);
    Page<Session> getSessions(Pageable pageable);
}
