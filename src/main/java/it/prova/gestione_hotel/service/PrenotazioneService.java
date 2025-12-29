package it.prova.gestione_hotel.service;

import it.prova.gestione_hotel.dto.PrenotazioneDto;
import it.prova.gestione_hotel.model.Prenotazione;

import java.util.Set;

public interface PrenotazioneService {
    Set<PrenotazioneDto> getAll();
    PrenotazioneDto findById(Long id);
    void addReservation(PrenotazioneDto prenotazioneDto);
    void modifyReservation(PrenotazioneDto prenotazioneDto);
    void deleteReservation(Long id);
    void logicDeleteReservation(Long id);
}
