package it.prova.gestione_hotel.specification;

import it.prova.gestione_hotel.dto.UtenteDtoFiltro;
import it.prova.gestione_hotel.model.Utente;
import jakarta.persistence.criteria.Predicate;
import org.apache.catalina.User;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class UtenteSpecification {

    public static Specification<Utente> findByNameAndSurname (UtenteDtoFiltro filter){
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter.getNome() != null && !filter.getCognome().isBlank()) {
                predicates.add(
                        cb.like(
                                cb.lower(root.get("nome")),
                                "%" + filter.getNome().toLowerCase() + "%"
                        )
                );
            }

            if (filter.getCognome() != null && !filter.getCognome().isEmpty()) {
                predicates.add(
                        cb.like(
                                cb.lower(root.get("cognome")),
                                "%" + filter.getCognome().toLowerCase() + "%"
                        )
                );
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
