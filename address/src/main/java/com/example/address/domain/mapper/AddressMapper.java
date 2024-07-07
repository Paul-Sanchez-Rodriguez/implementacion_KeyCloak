package com.example.address.domain.mapper;

import com.example.address.domain.dto.AddressRequestDto;
import com.example.address.domain.dto.AddressResponseDto;
import com.example.address.domain.model.Address;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AddressMapper {

    public static Address toModel(AddressRequestDto address){
        return new Address(
                address.getAddress(),
                address.getIdadolescent(),
                address.getActive(),
                address.getUbigeo()
        );
    }

    public static Address toModel(Integer id, AddressRequestDto address){
        return new Address(
                id,
                address.getAddress(),
                address.getIdadolescent(),
                address.getActive(),
                address.getUbigeo()
        );
    }

    public static AddressResponseDto toDto(Address address){
        return new AddressResponseDto(
                address.getId(),
                address.getAddress(),
                address.getIdadolescent(),
                address.getActive(),
                address.getUbigeo()
        );
    }
}
