package com.springBajo8.springBajo8.service.impl;

//import com.yoandypv.reactivestack.messages.domain.Message;
//import com.yoandypv.reactivestack.messages.repository.MessageRepository;
//import com.yoandypv.reactivestack.messages.service.MessageService;
import com.springBajo8.springBajo8.domain.citasDTOReactiva;
import com.springBajo8.springBajo8.domain.citasDTOReactiva;
import com.springBajo8.springBajo8.repository.IcitasReactivaRepository;
import com.springBajo8.springBajo8.service.IcitasReactivaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class citasReactivaServiceImpl implements IcitasReactivaService {

    @Autowired
    private IcitasReactivaRepository IcitasReactivaRepository;

    @Override
    public Mono<citasDTOReactiva> save(citasDTOReactiva citasDTOReactiva) {
        return IcitasReactivaRepository.save(citasDTOReactiva);
    }

    @Override
    public Mono<citasDTOReactiva> delete(String id) {
        return IcitasReactivaRepository
                .findById(id)
                .flatMap(p -> this.IcitasReactivaRepository.deleteById(p.getId()).thenReturn(p));
    }

    @Override
    public Mono<citasDTOReactiva> update(String id, citasDTOReactiva citasDTOReactiva) {
        return IcitasReactivaRepository.findById(id)
                .flatMap(citasDTOReactiva1 -> {
                    citasDTOReactiva.setId(id);
                    return save(citasDTOReactiva);
                })
                .switchIfEmpty(Mono.empty());
    }

    @Override
    public Flux<citasDTOReactiva> findByIdPaciente(String idPaciente) {
        return IcitasReactivaRepository.findByIdPaciente(idPaciente);
    }


    @Override
    public Flux<citasDTOReactiva> findAll() {
        return IcitasReactivaRepository.findAll();
    }

    @Override
    public Mono<citasDTOReactiva> findById(String id) {
        return IcitasReactivaRepository.findById(id);
    }

    @Override
    public Mono<citasDTOReactiva> cancelarCitaReactiva(String id) {
        return IcitasReactivaRepository.findById(id)
                .flatMap(citasDTOReactiva -> {
                    if (citasDTOReactiva.getEstadoReservaCita()) {
                        citasDTOReactiva.setEstadoReservaCita(false);
                        return save(citasDTOReactiva);
                    }
                    return Mono.empty();
                });
    }

    @Override
    public Flux<citasDTOReactiva> buscarCitaPorFechaYHora(String fecha, String hora) {
        return IcitasReactivaRepository.findByfechaReservaCitaAndHoraReservaCita(fecha, hora);
    }

    @Override
    public String consultarMedico(String id) {
        citasDTOReactiva cita = IcitasReactivaRepository.findById(id).block();
        return cita != null
                ? cita.getNombreMedico().concat(" " + cita.getApellidosMedico())
                : "No se encontro la cita";
    }
}