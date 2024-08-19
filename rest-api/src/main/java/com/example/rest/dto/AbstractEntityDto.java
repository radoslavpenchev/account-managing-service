package com.example.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class AbstractEntityDto extends AbstractDto {

    @JsonProperty("id")
    private UUID id;

    public AbstractEntityDto() {
        // POJO
    }

    public AbstractEntityDto(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
