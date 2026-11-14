package com.klyachkov.afisha.repositories;

import com.klyachkov.afisha.models.Venue;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface VenueRepository extends JpaRepository<Venue, UUID> {

    List<Venue> findByCityId(UUID cityId);

    List<Venue> findByNameContainingIgnoreCase(String name);
}