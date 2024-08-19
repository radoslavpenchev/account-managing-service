package com.example.domain;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import java.util.UUID;

@MappedSuperclass
public abstract class AbstractEntity {

    @Id
    @GeneratedValue
    private UUID id;

    protected AbstractEntity() {
    }

    protected AbstractEntity(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}
