package com.soa.canete.Transaccional_Act_Teen.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Table(name = "activities_teenager")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransaccionalActTeen {

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


    public TransaccionalActTeen(Integer id_activities, Integer id_teenager, LocalDate start_date, String duration, String notes, String participation_status, String active) {
        this.id_activities = id_activities;
        this.id_teenager = id_teenager;
        this.start_date = start_date;
        this.duration = duration;
        this.notes = notes;
        this.participation_status = participation_status;
        this.active = active;
    }

}
