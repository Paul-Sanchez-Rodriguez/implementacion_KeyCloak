package com.example.address.service.impl;

import com.example.address.domain.dto.AddressRequestDto;
import com.example.address.domain.dto.AddressResponseDto;
import com.example.address.domain.mapper.AddressMapper;
import com.example.address.exception.ResourceNotFoundException;
import com.example.address.repository.AddressRepository;
import com.example.address.service.AddressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.example.address.domain.mapper.AddressMapper.toModel;


@Slf4j
@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;


    @Override
    public Mono<AddressResponseDto> findById(Integer id) {
        return this.addressRepository.findById(id)
                .map(AddressMapper::toDto);
    }

    @Override
    public Flux<AddressResponseDto> findAllActive() {
        return this.addressRepository.findAll()
                .filter(adolescent -> "A".equals(adolescent.getActive()))
                .map(AddressMapper::toDto);
    }

    @Override
    public Mono<AddressResponseDto> create(AddressRequestDto request) {
        return this.addressRepository.save(toModel(request))
                .map(AddressMapper::toDto);

    }

    @Override
    public Mono<AddressResponseDto> update(AddressRequestDto request, Integer id) {
        return this.addressRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Direccion no existente")))
                .flatMap(address -> this.addressRepository.save(toModel(address.getId(),request)))
                .map(AddressMapper::toDto);
    }

    @Override
    public Mono<Void> delete(Integer id) {
        return this.addressRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Id de direccion no existente")))
                .flatMap(address -> {
                    address.setActive("I");
                    return this.addressRepository.save(address);
                })
                .doOnSuccess(unused -> log.info("se elimino el ID " + id))
                .then();
    }

    @Override
    public Flux<AddressResponseDto> findByIdAolescent(Integer id) {
        return this.addressRepository.findByIdadolescent(id)
                .map(AddressMapper::toDto);
    }

    @Override
    public Flux<AddressResponseDto> findAllInactive() {
        return this.addressRepository.findAll()
                .filter(adolescent -> "I".equals(adolescent.getActive()))
                .map(AddressMapper::toDto);
    }
}
