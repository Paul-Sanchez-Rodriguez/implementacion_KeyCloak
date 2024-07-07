package com.soa.canete.transaccional_allocation_soa_canete.web;

import com.soa.canete.transaccional_allocation_soa_canete.domain.dto.DataTeenFuncionaryTransaccional;
import com.soa.canete.transaccional_allocation_soa_canete.domain.dto.Transaccional.TransaccionalAllocationRequestDto;
import com.soa.canete.transaccional_allocation_soa_canete.domain.dto.Transaccional.TransaccionalAllocationResponseDto;
import com.soa.canete.transaccional_allocation_soa_canete.domain.model.TransaccionalAllocation;
import com.soa.canete.transaccional_allocation_soa_canete.repository.TransaccionalAllocationRepository;
import com.soa.canete.transaccional_allocation_soa_canete.service.TransaccionalAllocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/transaccionalFuncionary")
@RequiredArgsConstructor
public class TransaccionalAllocationController {

    final TransaccionalAllocationRepository transaccionalAllocationRepository;

    final TransaccionalAllocationService transaccionalAllocationService;

    @GetMapping("/{idFamilyData}")
    public Mono<DataTeenFuncionaryTransaccional> getTransaccionDataById(@PathVariable Integer idFamilyData) {
        return this.transaccionalAllocationService.findById(idFamilyData);
    }

    @GetMapping("/listData")
    public Flux<DataTeenFuncionaryTransaccional> getDataCompleteTransaccional() {
        return this.transaccionalAllocationService.findAll();
    }

    @PostMapping
    public Mono<TransaccionalAllocation> saveNewDataTransaccional(@RequestBody TransaccionalAllocationRequestDto dto) {
        return this.transaccionalAllocationService.saveNewDataTransaccional(dto);
    }

    @PutMapping("/update/{id}")
    public Mono<TransaccionalAllocationResponseDto> updateDataTransaccional(@RequestBody TransaccionalAllocationRequestDto dto, @PathVariable Integer id) {
        return this.transaccionalAllocationService.updateDataTransaccional(dto,id);
    }

    @GetMapping("/listIdTutor/{idTutor}")
    public Flux<DataTeenFuncionaryTransaccional> getDataForIdTutor(@PathVariable Integer idTutor) {
        return this.transaccionalAllocationService.findAllIdtutor(idTutor);
    }
}
