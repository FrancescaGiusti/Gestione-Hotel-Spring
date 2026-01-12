package it.prova.gestione_hotel.repository;

import it.prova.gestione_hotel.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UtenteRepository extends JpaRepository<Utente, Long>, JpaSpecificationExecutor<Utente> {

    @Modifying
    @Query("update Utente u set u.creditoDisponibile = u.creditoDisponibile + :creditoDaAggiungere where u.id = :id")
    int addCredit(@Param("id") Long id, @Param("creditoDaAggiungere") double creditoDaAggiungere);

    Utente findByUsername(String username);
}
