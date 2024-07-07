package com.soa.canete.Transaccional_Act_Teen.domain.dto.Activities;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Column;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class ActivitiesRequestDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 8222253670338491507L;

    @Column
    private String name;
    @Column
    private String description;
    @Column
    private LocalDate date;
    @Column
    private String duration;
    @Column
    private String location;
    @Column
    private String active;
    @Column
    private String type_pronacej;
    @Column
    private String type_soa;
}
