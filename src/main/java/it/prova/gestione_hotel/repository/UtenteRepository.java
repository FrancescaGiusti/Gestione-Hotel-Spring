package it.prova.gestione_hotel.repository;

import it.prova.gestione_hotel.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtenteRepository extends JpaRepository<Utente, Long> {
}
