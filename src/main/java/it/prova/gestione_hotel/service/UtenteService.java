package it.prova.gestione_hotel.service;

import it.prova.gestione_hotel.dto.UtenteDto;
import it.prova.gestione_hotel.model.Utente;

import java.util.Set;

public interface UtenteService {
    Set<UtenteDto> getAll();
    UtenteDto findById(Long id);
    void addClient(UtenteDto utenteDto);
    void modifyClient(UtenteDto utenteDto);
    void deleteClient(Long id);
}
