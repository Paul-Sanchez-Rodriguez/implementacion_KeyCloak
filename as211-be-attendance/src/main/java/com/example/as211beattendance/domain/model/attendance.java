package com.example.as211beattendance.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table
@Getter
@Setter
@NoArgsConstructor
public class attendance {

    @Id
    private Integer id;
    private Integer idactiviti;
    private Integer idadolescente;
    private String asistencia;
    private String active;


    public attendance(Integer idactiviti, Integer idadolescente, String asistencia, String active) {
        this.idactiviti = idactiviti;
        this.idadolescente = idadolescente;
        this.asistencia = asistencia;
        this.active = active;
    }

    public attendance(Integer id, Integer idactiviti, Integer idadolescente, String asistencia, String active) {
        this.id = id;
        this.idactiviti = idactiviti;
        this.idadolescente = idadolescente;
        this.asistencia = asistencia;
        this.active = active;
    }
}
