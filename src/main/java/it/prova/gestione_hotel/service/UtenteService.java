package it.prova.gestione_hotel.service;

import it.prova.gestione_hotel.model.Utente;

import java.util.Set;

public interface UtenteService {
    Set<Utente> getAll();
    Utente findById(Long id);
    void addClient(Utente utente);
    void modifyClient(Utente utente);
    void deleteClient(Long id);
}
