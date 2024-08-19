package com.example.bl.session;

import com.example.bl.transformer.account.SessionTransformer;
import com.example.domain.Session;
import com.example.persistence.SessionRepository;
import com.example.rest.dto.session.SessionDto;
import com.example.rest.dto.session.SessionsDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public class SessionService {

    private final SessionRepository sessionRepository;

    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public SessionDto createSession(SessionDto session) {
        if (session.getId() != null) {
            throw new IllegalArgumentException("Session already exist!");   // This should be conflict exception
        }

        final Session newSession = SessionTransformer.instance.createSource(session);
        final Session savedSession = sessionRepository.save(newSession);
        return SessionTransformer.instance.createTarget(savedSession);
    }

    public void deleteSession(UUID id) {
        sessionRepository.deleteById(id);
    }

    public SessionsDto getSessions(int page, int size) {
        final Pageable pageable = PageRequest.of(page - 1, size);
        final Page<Session> sessions = sessionRepository.findAll(pageable);
        final List<SessionDto> sessionDtos = sessions.stream().map(SessionTransformer.instance::createTarget).toList();
        return new SessionsDto(sessionDtos);
    }
}
