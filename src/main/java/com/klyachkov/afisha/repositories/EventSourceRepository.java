package com.klyachkov.afisha.repositories;

import com.klyachkov.afisha.models.EventSource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface EventSourceRepository extends JpaRepository<EventSource, UUID> {

    List<EventSource> findByEventId(UUID eventId);

    boolean existsBySourceHash(String sourceHash);
}