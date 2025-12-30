package it.prova.gestione_hotel.repository;

import it.prova.gestione_hotel.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {
    Set<Hotel> findByCitta(String citta);
}
