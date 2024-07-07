package com.example.as211beattendance.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class attendanceViewDto {

    private static final long serialVersionUID = 8222253670338491507L;
    private Integer id;
    private Integer idactiviti;
    private Integer idadolescente;
    private String name;
    private String asistencia;
    private String active;
}
