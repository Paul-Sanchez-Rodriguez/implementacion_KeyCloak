package com.example.address.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddressRequestDto {

    private static final long serialVersionUID = 8222253670338491507L;
    private String address;
    private Integer idadolescent;
    private String active;
    private String ubigeo;
}
