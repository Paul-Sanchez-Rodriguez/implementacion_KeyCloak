package com.soa.canete.Transaccional_Act_Teen.domain.dto.Transaccional;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class TransaccionalAllocationResponseDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 8222253670338491507L;

    @Id
    private Integer id_act_teen;
    @Column("id_activities")
    private Integer id_activities;
    @Column("id_teenager")
    private Integer id_teenager;
    @Column
    private LocalDate start_date;
    @Column
    private String duration;
    @Column
    private String notes;
    @Column
    private String participation_status;
    @Column
    private String active;

}
