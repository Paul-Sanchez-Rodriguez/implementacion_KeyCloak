package com.example.address.service;

import com.example.address.domain.dto.AddressRequestDto;
import com.example.address.domain.dto.AddressResponseDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AddressService {

    Mono<AddressResponseDto> findById(Integer id);
    Flux<AddressResponseDto> findAllActive();
    Mono<AddressResponseDto> create(AddressRequestDto request);
    Mono<AddressResponseDto> update(AddressRequestDto request, Integer id);
    Mono<Void> delete(Integer id);

    Flux<AddressResponseDto> findByIdAolescent(Integer id);
    Flux<AddressResponseDto> findAllInactive();

}
