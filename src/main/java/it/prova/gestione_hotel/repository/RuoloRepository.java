package it.prova.gestione_hotel.repository;

import it.prova.gestione_hotel.model.Codice;
import it.prova.gestione_hotel.model.Ruolo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface RuoloRepository extends JpaRepository<Ruolo, Long> {
    Set<Ruolo> findByCodice (Codice codice);
}
