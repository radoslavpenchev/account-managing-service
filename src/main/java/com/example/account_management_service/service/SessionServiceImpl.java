package com.example.account_management_service.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.account_management_service.model.Session;
import com.example.account_management_service.repository.SessionRepository;

@Service
public class SessionServiceImpl implements SessionService {

    @Autowired
    private SessionRepository sessionRepository;

    @Override
    public Session createSession(Session session) {
        return sessionRepository.save(session);
    }

    @Override
    public void deleteSession(UUID id) {
        sessionRepository.deleteById(id);
    }

    @Override
    public Page<Session> getSessions(Pageable pageable) {
        return sessionRepository.findAll(pageable);
    }
}
