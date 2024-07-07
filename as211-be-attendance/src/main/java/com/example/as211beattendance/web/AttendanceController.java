package com.example.as211beattendance.web;

import com.example.as211beattendance.domain.dto.AttemdanceRequestDto;
import com.example.as211beattendance.domain.dto.AttendanceResponseDto;
import com.example.as211beattendance.domain.dto.attendanceViewDto;
import com.example.as211beattendance.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/v1/attendance", produces = "application/json")
@RequiredArgsConstructor
public class AttendanceController {
    public final AttendanceService attendanceService;

    @GetMapping()
    public Flux<AttendanceResponseDto> findAll(){
        return this.attendanceService.findAll();
    }

    @GetMapping(value = "/{id}")
    public Mono<AttendanceResponseDto> finById(@PathVariable Integer id){
        return this.attendanceService.findById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public Mono<AttendanceResponseDto> create(@RequestBody AttemdanceRequestDto dto){
        dto.setActive("A");
        return this.attendanceService.create(dto);
    }

    @PutMapping(value = "/{id}")
    public Mono<AttendanceResponseDto> update(@RequestBody AttemdanceRequestDto dto, @PathVariable Integer id){
        return this.attendanceService.update(dto,id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable Integer id){
        return this.attendanceService.delete(id);
    }


    @GetMapping(value = "/view/{id}")
    public Flux<attendanceViewDto> viewAttendance(@PathVariable Integer id){
        return this.attendanceService.viewAttendance(id);
    }
}
