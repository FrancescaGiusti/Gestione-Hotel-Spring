package it.prova.gestione_hotel.specification;

import it.prova.gestione_hotel.dto.CameraDtoFiltro;
import it.prova.gestione_hotel.dto.HotelDtoFiltro;
import it.prova.gestione_hotel.model.Camera;
import it.prova.gestione_hotel.model.Hotel;
import it.prova.gestione_hotel.model.Prenotazione;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class HotelSpecification {
    public static Specification<Hotel> searchByCity (HotelDtoFiltro filter) {
        return (root, query, cb) -> {

            List<Predicate> predicates = new ArrayList<>();
            if (filter.getCitta() != null && !filter.getCitta().isEmpty()) {
                predicates.add(
                        cb.equal(cb.lower(root.get("citta")), filter.getCitta().toLowerCase())
                );
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
