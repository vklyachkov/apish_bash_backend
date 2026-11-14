package com.klyachkov.afisha.repositories;
import com.klyachkov.afisha.models.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FeedbackRepository extends JpaRepository<Feedback, UUID> {
}