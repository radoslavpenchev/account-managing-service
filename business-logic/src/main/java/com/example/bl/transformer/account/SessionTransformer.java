package com.example.bl.transformer.account;

import com.example.bl.transformer.AbstractEntityTransformer;
import com.example.domain.Session;
import com.example.rest.dto.session.SessionDto;

public class SessionTransformer extends AbstractEntityTransformer<Session, SessionDto> {

    public static final SessionTransformer instance = new SessionTransformer();

    private SessionTransformer() {
        // Singleton
    }

    @Override
    public void copyToTarget(Session session, SessionDto sessionDto) {
        super.copyToTarget(session, sessionDto);

        sessionDto.setIpAddress(session.getIpAddress());
        sessionDto.setAccountId(session.getAccount().getId());
    }

    @Override
    public void copyToSource(SessionDto sessionDto, Session session) {
        super.copyToSource(sessionDto, session);

        session.setIpAddress(sessionDto.getIpAddress());
    }

    @Override
    public SessionDto createTarget(Session session) {
        if (session == null) {
            return null;
        }

        final SessionDto dto = new SessionDto();
        copyToTarget(session, dto);
        return dto;
    }

    @Override
    public Session createSource(SessionDto sessionDto) {
        if (sessionDto == null) {
            return null;
        }

        final Session session = new Session();
        copyToSource(sessionDto, session);
        return session;
    }
}
