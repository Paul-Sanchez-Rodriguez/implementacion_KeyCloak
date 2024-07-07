package com.soa.canete.Transaccional_Act_Teen.repository;

import com.soa.canete.Transaccional_Act_Teen.domain.model.TransaccionalActTeen;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface TransaccionalActTeenRepository extends ReactiveCrudRepository<TransaccionalActTeen, Integer> {

    @Query("SELECT * FROM activities_teenager WHERE id_activities = :id ORDER BY id_act_teen DESC ")
    Flux<TransaccionalActTeen> findactividaddetalleByIdactividad(Integer id);
}
