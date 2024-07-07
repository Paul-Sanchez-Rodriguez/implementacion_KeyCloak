package com.example.as211beattendance.service;

import com.example.as211beattendance.domain.dto.AttemdanceRequestDto;
import com.example.as211beattendance.domain.dto.AttendanceResponseDto;
import com.example.as211beattendance.domain.dto.attendanceViewDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AttendanceService {

    Mono<AttendanceResponseDto> findById(Integer id);
    Flux<AttendanceResponseDto> findAll();
    Mono<AttendanceResponseDto> create(AttemdanceRequestDto request);
    Mono<AttendanceResponseDto> update(AttemdanceRequestDto request, Integer id);
    Mono<Void> delete(Integer id);

    Flux<attendanceViewDto> viewAttendance(Integer id);
}
