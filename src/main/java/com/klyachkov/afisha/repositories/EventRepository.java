package com.klyachkov.afisha.repositories;

import com.klyachkov.afisha.models.Event;
import com.klyachkov.afisha.models.EventStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;
import java.util.List;
import java.util.Optional;

public interface EventRepository extends
        JpaRepository<Event, UUID>,
        JpaSpecificationExecutor<Event> {

    List<Event> findByStatus(EventStatus status);

    Optional<Event> findByDedupKey(String dedupKey);

    List<Event> findByTitleContainingIgnoreCase(String title);

    boolean existsByDedupKey(String dedupKey);
}