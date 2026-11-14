package com.klyachkov.afisha.repositories;
import com.klyachkov.afisha.models.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;
import java.util.Optional;

public interface CityRepository extends JpaRepository<City, UUID> {

    Optional<City> findByNameIgnoreCase(String name);

    boolean existsByNameIgnoreCase(String name);
}
