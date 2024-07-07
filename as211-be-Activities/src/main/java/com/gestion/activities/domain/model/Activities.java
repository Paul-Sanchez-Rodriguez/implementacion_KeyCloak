package com.gestion.activities.domain.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Table(name = "activities")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Activities {

    @Id
    private Integer id;

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


    public Activities(String name, String description, LocalDate date, String duration, String location, String active, String type_pronacej, String type_soa) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.duration = duration;
        this.location = location;
        this.active = active;
        this.type_pronacej = type_pronacej;
        this.type_soa = type_soa;
    }

}
