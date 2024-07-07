package com.soa.canete.Transaccional_Act_Teen.service.impl;

import com.soa.canete.Transaccional_Act_Teen.domain.dto.DataTeenActivitiesTransaccional;
import com.soa.canete.Transaccional_Act_Teen.domain.dto.Activities.ActivitiesResponseDto;
import com.soa.canete.Transaccional_Act_Teen.domain.dto.Transaccional.TransaccionalAllocationRequestDto;
import com.soa.canete.Transaccional_Act_Teen.domain.dto.Transaccional.TransaccionalAllocationResponseDto;
import com.soa.canete.Transaccional_Act_Teen.domain.mapper.TransaccionalActTeenMapper;
import com.soa.canete.Transaccional_Act_Teen.domain.model.TransaccionalActTeen;
import com.soa.canete.Transaccional_Act_Teen.service.TransaccionalActTeenService;
import com.soa.canete.Transaccional_Act_Teen.domain.dto.Teen.TeenResponseDto;
import com.soa.canete.Transaccional_Act_Teen.exception.ResourceNotFoundException;
import com.soa.canete.Transaccional_Act_Teen.repository.TransaccionalActTeenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Comparator;

import static com.soa.canete.Transaccional_Act_Teen.domain.mapper.TransaccionalActTeenMapper.toModel;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransaccionalActTeenImpl implements TransaccionalActTeenService {

    @Autowired
    private WebClient.Builder webClientBuilder;

    final TransaccionalActTeenRepository transaccionalActTeenRepository;

    @Override
    public Mono<DataTeenActivitiesTransaccional> findById(Integer id_act_teen) {
        Mono<TransaccionalActTeen> family = transaccionalActTeenRepository.findById(id_act_teen);
        return family.flatMap(dataFamily -> {
            Mono<ActivitiesResponseDto> funcionaryResponseDtoMono = webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8083/ms-soa/" + dataFamily.getId_activities())
                    .retrieve()
                    .bodyToMono(ActivitiesResponseDto.class);
            Mono<TeenResponseDto> teenResponseDtoMono = webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8082/api/teenData/" + dataFamily.getId_teenager())
                    .retrieve()
                    .bodyToMono(TeenResponseDto.class);
            return funcionaryResponseDtoMono.zipWith(teenResponseDtoMono).map(dataGeneral -> {
                ActivitiesResponseDto legalGuardianResponseDtoData = dataGeneral.getT1();
                TeenResponseDto adolescentResponseDtoData = dataGeneral.getT2();
                DataTeenActivitiesTransaccional dataTeenActivitiesTransaccional = new DataTeenActivitiesTransaccional();
                //dataTeenActivitiesTransaccional.setTransaccionalActTeen(dataFamily);
                //dataTeenActivitiesTransaccional.setTeenResponseDto(adolescentResponseDtoData);
                //dataTeenActivitiesTransaccional.setActivitiesResponseDto(legalGuardianResponseDtoData);

                dataTeenActivitiesTransaccional.setTeeneger(adolescentResponseDtoData);
                dataTeenActivitiesTransaccional.setActivities(legalGuardianResponseDtoData);
                dataTeenActivitiesTransaccional.setStart_date(dataFamily.getStart_date());
                dataTeenActivitiesTransaccional.setDuration(dataFamily.getDuration());
                dataTeenActivitiesTransaccional.setNotes(dataFamily.getNotes());
                dataTeenActivitiesTransaccional.setParticipation_status(dataFamily.getParticipation_status());
                dataTeenActivitiesTransaccional.setActive(dataFamily.getActive());
                dataTeenActivitiesTransaccional.setId_actividadDetalle(dataFamily.getId_act_teen());
                return dataTeenActivitiesTransaccional;
            });
        });
    }

    @Override
    public Flux<DataTeenActivitiesTransaccional> findAll() {
        Flux<TransaccionalActTeen> family = transaccionalActTeenRepository.findAll().sort(Comparator.comparing(TransaccionalActTeen::getId_act_teen).reversed());
        return family.flatMap(dataFamily -> {
            Mono<ActivitiesResponseDto> funcionaryResponseDtoMono = webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8083/ms-soa/" + dataFamily.getId_activities())
                    .retrieve()
                    .bodyToMono(ActivitiesResponseDto.class);
            Mono<TeenResponseDto> teenResponseDtoMono = webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8082/api/teenData/" + dataFamily.getId_teenager())
                    .retrieve()
                    .bodyToMono(TeenResponseDto.class);
            return funcionaryResponseDtoMono.zipWith(teenResponseDtoMono).map(dataGeneral -> {
                ActivitiesResponseDto legalGuardianResponseDtoData = dataGeneral.getT1();
                TeenResponseDto adolescentResponseDtoData = dataGeneral.getT2();
                DataTeenActivitiesTransaccional dataTeenActiitiesTransaccional = new DataTeenActivitiesTransaccional();
                dataTeenActiitiesTransaccional.setTeeneger(adolescentResponseDtoData);
                dataTeenActiitiesTransaccional.setActivities(legalGuardianResponseDtoData);
                dataTeenActiitiesTransaccional.setStart_date(dataFamily.getStart_date());
                dataTeenActiitiesTransaccional.setDuration(dataFamily.getDuration());
                dataTeenActiitiesTransaccional.setNotes(dataFamily.getNotes());
                dataTeenActiitiesTransaccional.setParticipation_status(dataFamily.getParticipation_status());
                dataTeenActiitiesTransaccional.setActive(dataFamily.getActive());
                dataTeenActiitiesTransaccional.setId_actividadDetalle(dataFamily.getId_act_teen());
                return dataTeenActiitiesTransaccional;
            });
        });
    }

    @Override
    public Flux<DataTeenActivitiesTransaccional> findAllDataActive() {
        Flux<TransaccionalActTeen> family = transaccionalActTeenRepository.findAll()
                .sort(Comparator.comparing(TransaccionalActTeen::getId_act_teen).reversed())
                .filter((active) -> active.getActive().equals("A"));
        return family.flatMap(dataFamily -> {
            Mono<ActivitiesResponseDto> funcionaryResponseDtoMono = webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8083/ms-soa/" + dataFamily.getId_activities())
                    .retrieve()
                    .bodyToMono(ActivitiesResponseDto.class);
            Mono<TeenResponseDto> teenResponseDtoMono = webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8082/api/teenData/" + dataFamily.getId_teenager())
                    .retrieve()
                    .bodyToMono(TeenResponseDto.class);
            return funcionaryResponseDtoMono.zipWith(teenResponseDtoMono).map(dataGeneral -> {
                ActivitiesResponseDto legalGuardianResponseDtoData = dataGeneral.getT1();
                TeenResponseDto adolescentResponseDtoData = dataGeneral.getT2();
                DataTeenActivitiesTransaccional dataTeenFuncionaryTransaccional = new DataTeenActivitiesTransaccional();
                dataTeenFuncionaryTransaccional.setTeeneger(adolescentResponseDtoData);
                dataTeenFuncionaryTransaccional.setActivities(legalGuardianResponseDtoData);
                dataTeenFuncionaryTransaccional.setStart_date(dataFamily.getStart_date());
                dataTeenFuncionaryTransaccional.setDuration(dataFamily.getDuration());
                dataTeenFuncionaryTransaccional.setNotes(dataFamily.getNotes());
                dataTeenFuncionaryTransaccional.setParticipation_status(dataFamily.getParticipation_status());
                dataTeenFuncionaryTransaccional.setActive(dataFamily.getActive());
                dataTeenFuncionaryTransaccional.setId_actividadDetalle(dataFamily.getId_act_teen());
                return dataTeenFuncionaryTransaccional;
            });
        });
    }

    @Override
    public Flux<DataTeenActivitiesTransaccional> findAllDataInactive() {
        Flux<TransaccionalActTeen> family = transaccionalActTeenRepository.findAll()
                .sort(Comparator.comparing(TransaccionalActTeen::getId_act_teen).reversed())
                .filter((active) -> active.getActive().equals("I"));;
        return family.flatMap(dataFamily -> {
            Mono<ActivitiesResponseDto> funcionaryResponseDtoMono = webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8083/ms-soa/" + dataFamily.getId_activities())
                    .retrieve()
                    .bodyToMono(ActivitiesResponseDto.class);
            Mono<TeenResponseDto> teenResponseDtoMono = webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8082/api/teenData/" + dataFamily.getId_teenager())
                    .retrieve()
                    .bodyToMono(TeenResponseDto.class);
            return funcionaryResponseDtoMono.zipWith(teenResponseDtoMono).map(dataGeneral -> {
                ActivitiesResponseDto legalGuardianResponseDtoData = dataGeneral.getT1();
                TeenResponseDto adolescentResponseDtoData = dataGeneral.getT2();
                DataTeenActivitiesTransaccional dataTeenActivitiesTransaccional = new DataTeenActivitiesTransaccional();
                dataTeenActivitiesTransaccional.setTeeneger(adolescentResponseDtoData);
                dataTeenActivitiesTransaccional.setActivities(legalGuardianResponseDtoData);
                dataTeenActivitiesTransaccional.setStart_date(dataFamily.getStart_date());
                dataTeenActivitiesTransaccional.setDuration(dataFamily.getDuration());
                dataTeenActivitiesTransaccional.setNotes(dataFamily.getNotes());
                dataTeenActivitiesTransaccional.setParticipation_status(dataFamily.getParticipation_status());
                dataTeenActivitiesTransaccional.setActive(dataFamily.getActive());
                dataTeenActivitiesTransaccional.setId_actividadDetalle(dataFamily.getId_act_teen());
                return dataTeenActivitiesTransaccional;
            });
        });
    }

    @Override
    public Mono<TransaccionalAllocationResponseDto> saveNewDataTransaccional(TransaccionalAllocationRequestDto request) {
        return this.transaccionalActTeenRepository.save(TransaccionalActTeenMapper.toModel(request))
                .map(TransaccionalActTeenMapper::toDto);
    }

    @Override
    public Mono<TransaccionalAllocationResponseDto> updateDataTransaction(TransaccionalAllocationRequestDto request, Integer id_act_teen) {
        return this.transaccionalActTeenRepository.findById(id_act_teen)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("El identificador: " + id_act_teen + " no fue encontrado")))
                .flatMap((dataAsignationOrTransaction) -> this.transaccionalActTeenRepository.save(TransaccionalActTeenMapper.toModel(request, dataAsignationOrTransaction.getId_act_teen())))
                .map(TransaccionalActTeenMapper::toDto);
    }

    @Override
    public Mono<TransaccionalAllocationResponseDto> deleteLogicalTransaction(Integer id_act_teen) {
        return this.transaccionalActTeenRepository.findById(id_act_teen)
                .map((deleteData) -> {
                    deleteData.setActive("I");
                    return deleteData;
                })
                .flatMap(transaccionalActTeenRepository::save)
                .map(TransaccionalActTeenMapper::toDto);
    }

    @Override
    public Mono<TransaccionalAllocationResponseDto> reactiveLogicalTransaction(Integer id_act_teen) {
        return this.transaccionalActTeenRepository.findById(id_act_teen)
                .map((deleteData) -> {
                    deleteData.setActive("A");
                    return deleteData;
                })
                .flatMap(transaccionalActTeenRepository::save)
                .map(TransaccionalActTeenMapper::toDto);
    }

    @Override
    public Flux<DataTeenActivitiesTransaccional> findByIdActividad(Integer id) {
        return this.transaccionalActTeenRepository.findactividaddetalleByIdactividad(id)
                .flatMap(response ->{
                    Mono<ActivitiesResponseDto> funcionaryResponseDtoMono = webClientBuilder.build()
                            .get()
                            .uri("http://localhost:8083/ms-soa/" + response.getId_activities())
                            .retrieve()
                            .bodyToMono(ActivitiesResponseDto.class);
                    Mono<TeenResponseDto> teenResponseDtoMono = webClientBuilder.build()
                            .get()
                            .uri("http://localhost:8082/api/teenData/" + response.getId_teenager())
                            .retrieve()
                            .bodyToMono(TeenResponseDto.class);
                    return funcionaryResponseDtoMono.zipWith(teenResponseDtoMono).map(dataGeneral -> {
                        ActivitiesResponseDto legalGuardianResponseDtoData = dataGeneral.getT1();
                        TeenResponseDto adolescentResponseDtoData = dataGeneral.getT2();
                        DataTeenActivitiesTransaccional dataTeenActivitiesTransaccional = new DataTeenActivitiesTransaccional();
                        dataTeenActivitiesTransaccional.setTeeneger(adolescentResponseDtoData);
                        dataTeenActivitiesTransaccional.setActivities(legalGuardianResponseDtoData);
                        dataTeenActivitiesTransaccional.setStart_date(response.getStart_date());
                        dataTeenActivitiesTransaccional.setDuration(response.getDuration());
                        dataTeenActivitiesTransaccional.setNotes(response.getNotes());
                        dataTeenActivitiesTransaccional.setParticipation_status(response.getParticipation_status());
                        dataTeenActivitiesTransaccional.setActive(response.getActive());
                        dataTeenActivitiesTransaccional.setId_actividadDetalle(response.getId_act_teen());
                        return dataTeenActivitiesTransaccional;
                    });
                });
    }
}
