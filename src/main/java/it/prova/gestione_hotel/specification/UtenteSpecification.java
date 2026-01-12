package it.prova.gestione_hotel.specification;

import it.prova.gestione_hotel.dto.UtenteDtoFiltro;
import it.prova.gestione_hotel.model.Utente;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class UtenteSpecification {

    public static Specification<Utente> searchWithFilter (UtenteDtoFiltro filter){
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter.getNome() != null && !filter.getNome().isBlank()) {
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

            if (filter.getCodiceFiscale() != null && !filter.getCodiceFiscale().isBlank()) {
                predicates.add(cb.equal(
                        cb.upper(root.get("codiceFiscale")),
                        filter.getCodiceFiscale().toUpperCase()
                ));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
