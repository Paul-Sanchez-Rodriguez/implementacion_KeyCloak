package com.gestion.activities.service.impl;

import com.gestion.activities.domain.dto.ActivitiesRequestDto;
import com.gestion.activities.domain.dto.ActivitiesResponseDto;
import com.gestion.activities.domain.mapper.ActivitiesMapper;
import com.gestion.activities.domain.model.Activities;
import com.gestion.activities.repository.ActivitiesRepository;
import com.gestion.activities.service.ActivitiesService;
import com.gestion.activities.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Comparator;
import java.util.Objects;

import static com.gestion.activities.domain.mapper.ActivitiesMapper.toModel;

@Slf4j
@Service
@RequiredArgsConstructor
public class ActivitiesImpl implements ActivitiesService {

    final ActivitiesRepository activitiesRepository;

    @Override
    public Mono<ActivitiesResponseDto> findById(Integer id) {
        return this.activitiesRepository.findById(id)
                .map(ActivitiesMapper::toDto);
    }

    @Override
    public Flux<ActivitiesResponseDto> findAll() {
        return this.activitiesRepository.findAll()
                .sort(Comparator.comparing(Activities::getId).reversed())
                .map(ActivitiesMapper::toDto);
    }

    @Override
    public Flux<ActivitiesResponseDto> findAllActive() {
        return this.activitiesRepository.findAll()
                .sort(Comparator.comparing(Activities::getId).reversed())
                .filter(active -> Objects.equals(active.getActive(), "A"))
                .map(ActivitiesMapper::toDto);
    }

    @Override
    public Flux<ActivitiesResponseDto> findAllInactive() {
        return this.activitiesRepository.findAll()
                .sort(Comparator.comparing(Activities::getId).reversed())
                .filter(active -> Objects.equals(active.getActive(), "I"))
                .map(ActivitiesMapper::toDto);
    }


    @Override
    public Mono<ActivitiesResponseDto> saveNewLegalGuardian(ActivitiesRequestDto request) {
        return this.activitiesRepository.save(toModel(request))
                .map(ActivitiesMapper::toDto);
    }

    @Override
    public Mono<ActivitiesResponseDto> updateLegalGuardian(ActivitiesRequestDto request, Integer id) {
        return this.activitiesRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("El identificador: " + id + "no fue encontrado.")))
                .flatMap(dataFuncionary -> this.activitiesRepository.save(toModel(request, dataFuncionary.getId())))
                .map(ActivitiesMapper::toDto);
    }

    @Override
    public Mono<ActivitiesResponseDto> deleteLogicalLegalGuardian(Integer id) {
        return this.activitiesRepository.findById(id)
                .map((delete) -> {
                    delete.setActive("I");
                    return delete;
                })
                .flatMap(activitiesRepository::save)
                .map(ActivitiesMapper::toDto);
    }

    @Override
    public Mono<ActivitiesResponseDto> reactiveLogicalLegalGuardian(Integer id) {
        return this.activitiesRepository.findById(id)
                .map((reactive) -> {
                    reactive.setActive("A");
                    return reactive;
                })
                .flatMap(activitiesRepository::save)
                .map(ActivitiesMapper::toDto);
    }

    @Override
    public Mono<Void> deleteLegalGuardian(Integer id) {
        return this.activitiesRepository.deleteById(id);
    }
}
