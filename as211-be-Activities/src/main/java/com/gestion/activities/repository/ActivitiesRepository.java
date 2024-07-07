package com.gestion.activities.repository;

import com.gestion.activities.domain.model.Activities;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ActivitiesRepository extends ReactiveCrudRepository<Activities, Integer> {
}
