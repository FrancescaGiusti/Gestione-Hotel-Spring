package it.prova.gestione_hotel.service;

import it.prova.gestione_hotel.dto.PrenotazioneDto;
import it.prova.gestione_hotel.dto.PrenotazioneDtoFiltro;
import it.prova.gestione_hotel.exception.EntityNotFoundException;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface PrenotazioneService {
    Set<PrenotazioneDto> getAll();

    PrenotazioneDto findById(Long id);

    void addReservation(PrenotazioneDto prenotazioneDto);

    void modifyReservation(PrenotazioneDto prenotazioneDto) throws EntityNotFoundException;

    void deleteReservation(Long id);

    void logicDeleteReservation(Long id) throws EntityNotFoundException;

    Set<PrenotazioneDto> findByAnnullataFalse();

    Set<PrenotazioneDto> getAllPageable(Pageable pageable);

    Set<PrenotazioneDto> filtraPrenotazioni(PrenotazioneDtoFiltro filter, Pageable pageable);
}
