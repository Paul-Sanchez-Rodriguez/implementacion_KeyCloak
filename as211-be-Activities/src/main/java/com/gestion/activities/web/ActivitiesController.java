package com.gestion.activities.web;

import com.gestion.activities.domain.dto.ActivitiesRequestDto;
import com.gestion.activities.domain.dto.ActivitiesResponseDto;
import com.gestion.activities.repository.ActivitiesRepository;
import com.gestion.activities.service.ActivitiesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/ms-soa")
@RequiredArgsConstructor
public class ActivitiesController {

    //private final Logger logger = LoggerFactory.getLogger(OperationalUnitController.class);


    final ActivitiesService activitiesService;

    final ActivitiesRepository activitiesRepository;

    @GetMapping("{id_act}")
    public Mono<ActivitiesResponseDto> getDataActivitiesById(@PathVariable Integer id_act) {
        return this.activitiesService.findById(id_act);
    }

    @GetMapping("/listData")
    public Flux<ActivitiesResponseDto> getDataActivitiesComplete() {
        return this.activitiesService.findAll();
    }

    //      logger.info("Entrando en getDataFuncionaryComplete"); // Mensaje de registro
    //      Flux<OperationalUnitResponseDto> data = this.operationalUnitService.findAll();
    //      logger.info("Saliendo de getDataFuncionaryComplete"); // Mensaje de registro
    //      return data;


    @GetMapping("/listData/active")
    public Flux<ActivitiesResponseDto> getDataActivitiesActive() { return this.activitiesService.findAllActive();
    }

    @GetMapping("/listData/inactive")
    public Flux<ActivitiesResponseDto> getDataActivitiesInactive() { return this.activitiesService.findAllInactive();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Mono<ActivitiesResponseDto> saveNewDataActivities(@RequestBody ActivitiesRequestDto dto) {
        return this.activitiesService.saveNewLegalGuardian(dto);
    }

    @PutMapping("/{id_act}")
    public Mono<ActivitiesResponseDto> updateDataActivities(@RequestBody ActivitiesRequestDto dto, @PathVariable Integer id_act) {
        return this.activitiesService.updateLegalGuardian(dto, id_act);
    }

    @PatchMapping("/deleteLogical/{id_act}")
    public Mono<ActivitiesResponseDto> deleteLogicalActivities(@PathVariable Integer id_act) {
        return this.activitiesService.deleteLogicalLegalGuardian(id_act);
    }

    @PatchMapping("/reactiveLogical/{id_act}")
    public Mono<ActivitiesResponseDto> reactiveLogicalActivities(@PathVariable Integer id_act) {
        return this.activitiesService.reactiveLogicalLegalGuardian(id_act);
    }

    @DeleteMapping("/{id_act}")
    public Mono<Void> deleteTotalActivities(@PathVariable Integer id_act) {
        return this.activitiesService.deleteLegalGuardian(id_act);
    }

}
