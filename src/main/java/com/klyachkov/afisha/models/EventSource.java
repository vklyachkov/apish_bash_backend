package com.klyachkov.afisha.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SourceType;

import java.util.UUID;

@Entity
@Table(name = "event_sources")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventSource {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @Column(nullable = false)
    private String sourceUrl;

    private String sourceHash;

    @Enumerated(EnumType.STRING)
    private SourceType sourceType;
}
