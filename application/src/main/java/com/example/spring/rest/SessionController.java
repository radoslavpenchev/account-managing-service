package com.example.spring.rest;

import com.example.bl.session.SessionService;
import com.example.rest.api.SessionsRestService;
import com.example.rest.dto.session.SessionDto;
import com.example.rest.dto.session.SessionsDto;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/sessions")
public class SessionController implements SessionsRestService {

    private final SessionService sessionService;

    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @PostMapping
    @Override
    public SessionDto createSession(SessionDto session) {
        return sessionService.createSession(session);
    }

    @GetMapping
    @Override
    public SessionsDto getSessions(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "20") int size) {
        return sessionService.getSessions(page, size);
    }

    @DeleteMapping("/{id}")
    public void deleteSession(@PathVariable UUID id) {
        sessionService.deleteSession(id);
    }
}
