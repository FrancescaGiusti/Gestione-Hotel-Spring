package it.prova.gestione_hotel.service;

import it.prova.gestione_hotel.dto.UtenteAggiungiCreditoDto;
import it.prova.gestione_hotel.dto.UtenteDto;
import it.prova.gestione_hotel.dto.UtenteDtoFiltro;
import it.prova.gestione_hotel.exception.EntityNotFoundException;
import it.prova.gestione_hotel.model.Utente;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface UtenteService {
    Set<UtenteDto> getAll();
    UtenteDto findById(Long id);
    void addClient(UtenteDto utenteDto);
    void modifyClient(UtenteDto utenteDto) throws EntityNotFoundException;
    void deleteClient(Long id) throws EntityNotFoundException;
    void addCredito(UtenteAggiungiCreditoDto utenteAggiungiCreditoDto) throws EntityNotFoundException;
    Set<UtenteDto> getAllPaginated(Pageable pageable);
    Set<UtenteDto> getAllFiltered(UtenteDtoFiltro filter, Pageable pageable);
}
