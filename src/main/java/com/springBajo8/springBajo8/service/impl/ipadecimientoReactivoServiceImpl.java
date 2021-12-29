package com.springBajo8.springBajo8.service.impl;

import com.springBajo8.springBajo8.domain.PadecimientoDTOReactivo;
import com.springBajo8.springBajo8.repository.IpadecimientoReactivoRepository;
import com.springBajo8.springBajo8.service.IpadecimientoReactivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ipadecimientoReactivoServiceImpl implements IpadecimientoReactivoService {

    @Autowired
    private IpadecimientoReactivoRepository repository;

    @Override
    public Mono<PadecimientoDTOReactivo> save(PadecimientoDTOReactivo padecimientoDTOReactivo) {
        return repository.save(padecimientoDTOReactivo);
    }

    @Override
    public Mono<PadecimientoDTOReactivo> delete(String id) {
        return repository
                .findById(id)
                .flatMap(p -> repository.deleteById(p.getId()).thenReturn(p));
    }

    @Override
    public Mono<PadecimientoDTOReactivo> update(String id, PadecimientoDTOReactivo padecimientoDTOReactivo) {
        return repository.findById(id)
                .flatMap(citasDTOReactiva1 -> {
                    padecimientoDTOReactivo.setId(id);
                    return save(padecimientoDTOReactivo);
                })
                .switchIfEmpty(Mono.empty());
    }

    @Override
    public Flux<PadecimientoDTOReactivo> findByIdPaciente(String idPaciente) {
        return repository.findByIdPaciente(idPaciente);
    }

    @Override
    public Flux<PadecimientoDTOReactivo> findAll() {
        return repository.findAll();
    }
}