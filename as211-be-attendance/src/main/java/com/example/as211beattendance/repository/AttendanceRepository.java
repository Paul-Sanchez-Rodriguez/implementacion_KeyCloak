package com.example.as211beattendance.repository;

import com.example.as211beattendance.domain.model.attendance;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface AttendanceRepository extends ReactiveCrudRepository<attendance, Integer> {

    @Query("select * from attendance WHERE idactiviti = :id ORDER BY id ASC ")
    Flux<attendance> findIdActividad(Integer id);
}
