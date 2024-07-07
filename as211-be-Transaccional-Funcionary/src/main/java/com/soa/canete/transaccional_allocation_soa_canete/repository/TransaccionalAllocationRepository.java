package com.soa.canete.transaccional_allocation_soa_canete.repository;

import com.soa.canete.transaccional_allocation_soa_canete.domain.dto.Transaccional.TransaccionalAllocationResponseDto;
import com.soa.canete.transaccional_allocation_soa_canete.domain.model.TransaccionalAllocation;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TransaccionalAllocationRepository extends ReactiveCrudRepository<TransaccionalAllocation, Integer> {


    @Query("SELECT * FROM funcionarios_adolescente WHERE id_adolescente = :id")
    Mono<TransaccionalAllocationResponseDto> findById_adolescente(Integer id);

    @Query("SELECT * FROM funcionarios_adolescente WHERE id_funcionary = :id")
    Flux<TransaccionalAllocation> findByidtutor(Integer id);
}
