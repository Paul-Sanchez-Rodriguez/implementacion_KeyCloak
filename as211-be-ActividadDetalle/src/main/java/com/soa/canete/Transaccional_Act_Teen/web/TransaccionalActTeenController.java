package com.soa.canete.Transaccional_Act_Teen.web;

import com.soa.canete.Transaccional_Act_Teen.domain.dto.DataTeenActivitiesTransaccional;
import com.soa.canete.Transaccional_Act_Teen.domain.dto.Transaccional.TransaccionalAllocationRequestDto;
import com.soa.canete.Transaccional_Act_Teen.domain.dto.Transaccional.TransaccionalAllocationResponseDto;
import com.soa.canete.Transaccional_Act_Teen.service.TransaccionalActTeenService;
import com.soa.canete.Transaccional_Act_Teen.repository.TransaccionalActTeenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/transaccionalData")
@RequiredArgsConstructor
public class TransaccionalActTeenController {

    final TransaccionalActTeenRepository transaccionalAllocationRepository;

    final TransaccionalActTeenService transaccionalActTeenService;

    @GetMapping("/{idFamilyData}")
    public Mono<DataTeenActivitiesTransaccional> getTransaccionDataById(@PathVariable Integer idFamilyData) {
        return this.transaccionalActTeenService.findById(idFamilyData);
    }

    @GetMapping("/listData")
    public Flux<DataTeenActivitiesTransaccional> getDataCompleteTransaccional() {
        return this.transaccionalActTeenService.findAll();
    }

    @GetMapping("/listData/active")
    public Flux<DataTeenActivitiesTransaccional> getDataActiveAsignation() {
        return this.transaccionalActTeenService.findAllDataActive();
    }

    @GetMapping("/listData/inactive")
    public Flux<DataTeenActivitiesTransaccional> getDataInativeAsignation() {
        return this.transaccionalActTeenService.findAllDataInactive();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Mono<TransaccionalAllocationResponseDto> saveNewDataTransaccional(@RequestBody TransaccionalAllocationRequestDto dto) {
        return this.transaccionalActTeenService.saveNewDataTransaccional(dto);
    }

    @PutMapping("/{id_actteend}")
    public Mono<TransaccionalAllocationResponseDto> updateDataTransaccional(@RequestBody TransaccionalAllocationRequestDto dto, @PathVariable Integer id_act_teen) {
        return this.transaccionalActTeenService.updateDataTransaction(dto, id_act_teen);
    }

    @PatchMapping("/deleteLogical/{id_actteend}")
    public Mono<TransaccionalAllocationResponseDto> deleteDataTransaccional(@PathVariable Integer id_act_teen) {
        return this.transaccionalActTeenService.deleteLogicalTransaction(id_act_teen);
    }

    @PatchMapping("/reactiveLogical/{id_actteend}")
    public Mono<TransaccionalAllocationResponseDto> reactiveDataTransaccional(@PathVariable Integer id_act_teen) {
        return this.transaccionalActTeenService.reactiveLogicalTransaction(id_act_teen);
    }

    @GetMapping(value = "/Actividad/{id}")
    public Flux<DataTeenActivitiesTransaccional> finByIdActividad(@PathVariable Integer id){
        return this.transaccionalActTeenService.findByIdActividad(id);
    }

}
