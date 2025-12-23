package it.prova.gestione_hotel.service;

import it.prova.gestione_hotel.model.Prenotazione;

import java.util.Set;

public interface PrenotazioneService {
    Set<Prenotazione> getAll();
    Prenotazione findById(Long id);
    void addReservation(Prenotazione prenotazione);
    void modifyReservation(Prenotazione prenotazione);
    void deleteReservation(Long id);
    void logicDeleteReservation(Long id);
}
