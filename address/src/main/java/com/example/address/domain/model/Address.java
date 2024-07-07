package com.example.address.domain.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table
@Getter
@Setter
@NoArgsConstructor
public class Address {

    @Id
    private Integer id;
    private String address;
    private Integer idadolescent;
    private String active;
    private String ubigeo;

    public Address(String address, Integer idadolescent, String active, String ubigeo) {
        this.address = address;
        this.idadolescent = idadolescent;
        this.active = active;
        this.ubigeo = ubigeo;
    }

    public Address(Integer id, String address, Integer idadolescent, String active, String ubigeo) {
        this.id = id;
        this.address = address;
        this.idadolescent = idadolescent;
        this.active = active;
        this.ubigeo = ubigeo;
    }
}
