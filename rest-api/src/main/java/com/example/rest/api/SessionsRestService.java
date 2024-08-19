package com.example.rest.api;

import com.example.rest.dto.session.SessionDto;
import com.example.rest.dto.session.SessionsDto;

import java.util.UUID;

public interface SessionsRestService {

    SessionDto createSession(SessionDto session);

    SessionsDto getSessions(int page, int size);

    void deleteSession(UUID id);
}
