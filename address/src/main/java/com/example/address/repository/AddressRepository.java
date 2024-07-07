package com.example.address.repository;

import com.example.address.domain.model.Address;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AddressRepository extends ReactiveCrudRepository<Address, Integer> {
    @Query("SELECT * FROM address WHERE idadolescent = :id ORDER BY id DESC ")
    Flux<Address> findByIdadolescent(Integer id);
}
