package com.springBajo8.springBajo8.repository;

import com.springBajo8.springBajo8.domain.PadecimientoDTOReactivo;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface IpadecimientoReactivoRepository extends ReactiveMongoRepository<PadecimientoDTOReactivo, String> {
    Flux<PadecimientoDTOReactivo> findByIdPaciente(String idPaciente);
}