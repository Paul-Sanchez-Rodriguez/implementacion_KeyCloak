package com.soa.canete.Transaccional_Act_Teen.domain.dto;

import com.soa.canete.Transaccional_Act_Teen.domain.dto.Activities.ActivitiesResponseDto;
import com.soa.canete.Transaccional_Act_Teen.domain.dto.Teen.TeenResponseDto;
import com.soa.canete.Transaccional_Act_Teen.domain.model.TransaccionalActTeen;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataTeenActivitiesTransaccional {

    //private ActivitiesResponseDto activitiesResponseDto;
    //private TeenResponseDto teenResponseDto;
    //private TransaccionalActTeen transaccionalActTeen;

    private Integer id_actividadDetalle;
    private TeenResponseDto teeneger;
    private ActivitiesResponseDto activities;
    private LocalDate start_date;
    private String duration;
    private String notes;
    private String participation_status;
    private String active;

}
