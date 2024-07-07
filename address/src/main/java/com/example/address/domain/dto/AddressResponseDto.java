package com.example.address.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;


@Data
@AllArgsConstructor
public class AddressResponseDto implements Serializable {

    private static final long serialVersionUID = 8222253670338491507L;
    private Integer id;
    private String address;
    private Integer idadolescent;
    private String active;
    private String ubigeo;
}
