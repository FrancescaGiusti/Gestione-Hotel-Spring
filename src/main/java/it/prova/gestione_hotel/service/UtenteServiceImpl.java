package it.prova.gestione_hotel.service;

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
    public Set<Utente> getAll() {
        return utenteRepository.findAll().stream().collect(Collectors.toSet());
    }

    @Override
    public Utente findById(Long id) {
        return utenteRepository.findById(id).orElse(null);
    }

    @Override
    public void addClient(Utente utente) {
        if (utente == null)
            throw new RuntimeException("Input non valido");
        utenteRepository.save(utente);
    }

    @Override
    public void modifyClient(Utente utente) {
        Utente daModificare = utenteRepository.findById(utente.getId()).orElse(null);
        if (daModificare == null)
            throw new RuntimeException("l'utente che vuoi modificare non esiste");
        utenteRepository.save(daModificare);
    }

    @Override
    public void deleteClient(Long id) {
        Utente daEliminare = utenteRepository.findById(id).orElse(null);
        if (daEliminare == null)
            throw new RuntimeException("l'utente che vuoi eliminare non esiste");
        utenteRepository.save(daEliminare);
    }
}
