package com.example.account_management_service;

import com.example.bl.session.SessionService;
import com.example.domain.Session;
import com.example.persistence.SessionRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
public class SessionServiceImplTest {

    @Mock
    private SessionRepository sessionRepository;

    @InjectMocks
    private SessionService sessionService;

    @Test
    public void testCreateSession() {
        Session session = new Session();
        session.setIpAddress("192.168.1.1");

        when(sessionRepository.save(session)).thenReturn(session);

//        Session createdSession = sessionService.createSession(session); FIXME

//        assertNotNull(createdSession);
//        assertEquals("192.168.1.1", createdSession.getIpAddress());
        verify(sessionRepository, times(1)).save(session);
    }

    @Test
    public void testDeleteSession() {
        UUID id = UUID.randomUUID();

        doNothing().when(sessionRepository).deleteById(id);

        sessionService.deleteSession(id);

        verify(sessionRepository, times(1)).deleteById(id);
    }

    @Test
    public void testGetSessions() {
        PageRequest pageable = PageRequest.of(0, 10);
        Session session1 = new Session();
        Session session2 = new Session();
        Page<Session> page = new PageImpl<>(List.of(session1, session2));

        when(sessionRepository.findAll(pageable)).thenReturn(page);

//        Page<Session> sessions = sessionService.getSessions(pageable); FIXME

//        assertNotNull(sessions);
//        assertEquals(2, sessions.getTotalElements());
        verify(sessionRepository, times(1)).findAll(pageable);
    }
}
