package it.prova.gestione_hotel.specification;

import it.prova.gestione_hotel.dto.HotelDtoFiltro;
import it.prova.gestione_hotel.model.Hotel;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class HotelSpecification {
    public static Specification<Hotel> searchWithFilter (HotelDtoFiltro filter) {
        return (root, query, cb) -> {

            List<Predicate> predicates = new ArrayList<>();
            if (filter.getCitta() != null && !filter.getCitta().isEmpty()) {
                predicates.add(
                        cb.equal(cb.lower(root.get("citta")), filter.getCitta().toLowerCase())
                );
            }

            if (filter.getNome() != null && !filter.getCitta().isEmpty()) {
                predicates.add(
                        cb.like(
                                cb.lower(root.get("nome")),
                                "%" + filter.getNome().toLowerCase() + "%"
                        )
                );
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
