package com.klyachkov.afisha.specifications;

import com.klyachkov.afisha.models.Event;
import com.klyachkov.afisha.models.EventStatus;
import jakarta.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EventSpecification {

    public static Specification<Event> filter(
            UUID cityId,
            UUID categoryId,
            UUID venueId,
            LocalDateTime dateFrom,
            LocalDateTime dateTo,
            Integer maxPrice,
            Integer userAge
    ) {
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            // Показываем только опубликованные события
            predicates.add(
                    criteriaBuilder.equal(
                            root.get("status"),
                            EventStatus.PUBLISHED
                    )
            );

            // Фильтр по городу
            if (cityId != null) {
                predicates.add(
                        criteriaBuilder.equal(
                                root.get("city").get("id"),
                                cityId
                        )
                );
            }

            // Фильтр по категории
            if (categoryId != null) {
                predicates.add(
                        criteriaBuilder.equal(
                                root.get("category").get("id"),
                                categoryId
                        )
                );
            }

            // Фильтр по площадке
            if (venueId != null) {
                predicates.add(
                        criteriaBuilder.equal(
                                root.get("venue").get("id"),
                                venueId
                        )
                );
            }

            // Начало периода
            if (dateFrom != null) {
                predicates.add(
                        criteriaBuilder.greaterThanOrEqualTo(
                                root.get("dateStart"),
                                dateFrom
                        )
                );
            }

            // Конец периода
            if (dateTo != null) {
                predicates.add(
                        criteriaBuilder.lessThanOrEqualTo(
                                root.get("dateStart"),
                                dateTo
                        )
                );
            }

            // Максимальная цена
            if (maxPrice != null) {
                predicates.add(
                        criteriaBuilder.or(
                                criteriaBuilder.isTrue(root.get("isFree")),
                                criteriaBuilder.lessThanOrEqualTo(
                                        root.get("priceMin"),
                                        maxPrice
                                )
                        )
                );
            }

            // Возраст пользователя
            if (userAge != null) {
                predicates.add(
                        criteriaBuilder.lessThanOrEqualTo(
                                root.get("ageLimit"),
                                userAge
                        )
                );
            }

            return criteriaBuilder.and(
                    predicates.toArray(new Predicate[0])
            );
        };
    }
}

