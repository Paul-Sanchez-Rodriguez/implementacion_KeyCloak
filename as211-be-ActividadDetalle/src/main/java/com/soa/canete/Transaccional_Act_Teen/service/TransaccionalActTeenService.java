package com.soa.canete.Transaccional_Act_Teen.service;

import com.soa.canete.Transaccional_Act_Teen.domain.dto.DataTeenActivitiesTransaccional;
import com.soa.canete.Transaccional_Act_Teen.domain.dto.Transaccional.TransaccionalAllocationRequestDto;
import com.soa.canete.Transaccional_Act_Teen.domain.dto.Transaccional.TransaccionalAllocationResponseDto;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public interface TransaccionalActTeenService {

    Mono<DataTeenActivitiesTransaccional> findById(Integer id_act_teen);
    Flux<DataTeenActivitiesTransaccional> findAll();
    Flux<DataTeenActivitiesTransaccional> findAllDataActive();
    Flux<DataTeenActivitiesTransaccional> findAllDataInactive();
    Mono<TransaccionalAllocationResponseDto> saveNewDataTransaccional(TransaccionalAllocationRequestDto request);
    Mono<TransaccionalAllocationResponseDto> updateDataTransaction(TransaccionalAllocationRequestDto request, Integer id_act_teen);
    Mono<TransaccionalAllocationResponseDto> deleteLogicalTransaction(Integer id_act_teen);
    Mono<TransaccionalAllocationResponseDto> reactiveLogicalTransaction(Integer id_act_teen);
    Flux<DataTeenActivitiesTransaccional> findByIdActividad(Integer id);
}
