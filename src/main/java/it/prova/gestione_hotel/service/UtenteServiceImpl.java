package it.prova.gestione_hotel.service;

import it.prova.gestione_hotel.dto.UtenteDto;
import it.prova.gestione_hotel.model.Utente;
import it.prova.gestione_hotel.repository.UtenteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class UtenteServiceImpl implements UtenteService{
    private final UtenteRepository utenteRepository;

    @Autowired
    public UtenteServiceImpl(UtenteRepository utenteRepository) {
        this.utenteRepository = utenteRepository;
    }

    @Override
    public Set<UtenteDto> getAll() {
        return UtenteDto.fromModel(utenteRepository.findAll().stream().collect(Collectors.toSet()));
    }

    @Override
    public UtenteDto findById(Long id) {
        return UtenteDto.fromModelLight(utenteRepository.findById(id).orElse(null));
    }

    @Override
    public void addClient(UtenteDto utenteDto) {
        if (utenteDto == null)
            throw new RuntimeException("Input non valido");
        utenteRepository.save(utenteDto.toModel());
    }

    @Override
    public void modifyClient(UtenteDto utenteDto) {
        Utente daModificare = utenteRepository.findById(utenteDto.toModel().getId()).orElse(null);
        if (daModificare == null)
            throw new RuntimeException("l'utente che vuoi modificare non esiste");
        utenteRepository.save(utenteDto.toModel());
    }

    @Override
    public void deleteClient(Long id) {
        Utente daEliminare = utenteRepository.findById(id).orElse(null);
        if (daEliminare == null)
            throw new RuntimeException("l'utente che vuoi eliminare non esiste");
        utenteRepository.delete(daEliminare);
    }
}
