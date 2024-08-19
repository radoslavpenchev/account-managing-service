package com.example.rest.dto.session;

import com.example.rest.dto.AbstractDto;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class SessionsDto extends AbstractDto {

    @JsonProperty("sessions")
    private List<SessionDto> sessions = new ArrayList<>();

    public SessionsDto() {
        // POJO
    }

    public SessionsDto(List<SessionDto> sessions) {
        this.sessions = sessions;
    }

    public List<SessionDto> getSessions() {
        return sessions;
    }

    public void setSessions(List<SessionDto> sessions) {
        this.sessions = sessions;
    }
}
