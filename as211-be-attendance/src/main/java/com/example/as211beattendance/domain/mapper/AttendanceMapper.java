package com.example.as211beattendance.domain.mapper;

import com.example.as211beattendance.domain.dto.AttemdanceRequestDto;
import com.example.as211beattendance.domain.dto.AttendanceResponseDto;
import com.example.as211beattendance.domain.model.attendance;

public class AttendanceMapper {

    public static attendance toModel(AttemdanceRequestDto attemdanceRequestDto){
        return new attendance(
                attemdanceRequestDto.getIdactiviti(),
                attemdanceRequestDto.getIdadolescente(),
                attemdanceRequestDto.getAsistencia(),
                attemdanceRequestDto.getActive()
        );
    }

    public static attendance toModel(Integer id, AttemdanceRequestDto attemdanceRequestDto){
        return new attendance(
                id,
                attemdanceRequestDto.getIdactiviti(),
                attemdanceRequestDto.getIdadolescente(),
                attemdanceRequestDto.getAsistencia(),
                attemdanceRequestDto.getActive()
        );
    }

    public static AttendanceResponseDto toDto(attendance attendance){
        return new AttendanceResponseDto(
                attendance.getId(),
                attendance.getIdactiviti(),
                attendance.getIdadolescente(),
                attendance.getAsistencia(),
                attendance.getActive()
        );
    }
}
