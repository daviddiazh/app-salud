package com.springBajo8.springBajo8.service;

import com.springBajo8.springBajo8.domain.PadecimientoDTOReactivo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IpadecimientoReactivoService {
    Mono<PadecimientoDTOReactivo> save(PadecimientoDTOReactivo padecimientoDTOReactivo);

    Mono<PadecimientoDTOReactivo> delete(String id);

    Mono<PadecimientoDTOReactivo> update(String id, PadecimientoDTOReactivo padecimientoDTOReactivo);

    Flux<PadecimientoDTOReactivo> findByIdPaciente(String idPaciente);

    Flux<PadecimientoDTOReactivo> findAll();
}