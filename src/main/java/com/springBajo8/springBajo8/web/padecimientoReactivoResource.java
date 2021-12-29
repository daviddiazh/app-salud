package com.springBajo8.springBajo8.web;

import com.springBajo8.springBajo8.domain.citasDTOReactiva;
import com.springBajo8.springBajo8.domain.PadecimientoDTOReactivo;
import com.springBajo8.springBajo8.service.IpadecimientoReactivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class padecimientoReactivoResource {

    @Autowired
    private IpadecimientoReactivoService service;

    @PostMapping("/padecimientos")
    @ResponseStatus(HttpStatus.CREATED)
    private Mono<PadecimientoDTOReactivo> save(@RequestBody PadecimientoDTOReactivo padecimiento) {
        return service.save(padecimiento);
    }

    @DeleteMapping("/padecimientos/{id}")
    private Mono<ResponseEntity<PadecimientoDTOReactivo>> delete(@PathVariable("id") String id) {
        return service.delete(id)
                .flatMap(padecimiento -> Mono.just(ResponseEntity.ok(padecimiento)))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

    @PutMapping("/padecimientos/{id}")
    private Mono<ResponseEntity<PadecimientoDTOReactivo>> update(@PathVariable("id") String id, @RequestBody PadecimientoDTOReactivo padecimiento) {
        return service.update(id, padecimiento)
                .flatMap(padecimiento1 -> Mono.just(ResponseEntity.ok(padecimiento1)))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

    @GetMapping("/padecimientos/{idPaciente}/byidPaciente")
    private Flux<PadecimientoDTOReactivo> findByidPaciente(@PathVariable("idPaciente") String idPaciente) {
        return service.findByIdPaciente(idPaciente);
    }

    @GetMapping(value = "/padecimientos")
    private Flux<PadecimientoDTOReactivo> findAll() {
        return service.findAll();
    }

}