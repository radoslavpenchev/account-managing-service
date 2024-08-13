package com.example.account_management_service.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.account_management_service.model.Session;

public interface SessionRepository extends JpaRepository<Session, UUID> {
}
