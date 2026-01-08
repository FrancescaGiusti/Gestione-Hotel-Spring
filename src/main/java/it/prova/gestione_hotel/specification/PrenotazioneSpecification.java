package it.prova.gestione_hotel.specification;

import it.prova.gestione_hotel.dto.PrenotazioneDtoFiltro;
import it.prova.gestione_hotel.model.Prenotazione;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class PrenotazioneSpecification {
    public static Specification<Prenotazione> withFilter(PrenotazioneDtoFiltro filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter.getIdUtente() != null) {
                predicates.add(cb.equal(root.get("utente").get("id"), filter.getIdUtente()));
            }

            if (filter.getIdCamera() != null) {
                predicates.add(cb.equal(root.get("camera").get("id"), filter.getIdCamera()));
            }

            if (filter.getDataDa() != null && filter.getDataA() != null) {
                predicates.add(cb.and(
                        cb.lessThanOrEqualTo(root.get("dataInizioSoggiorno"), filter.getDataA()),
                        cb.greaterThanOrEqualTo(root.get("dataFineSoggiorno"), filter.getDataDa())
                ));
            } else if (filter.getDataDa() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("dataFineSoggiorno"), filter.getDataDa()));
            } else if (filter.getDataA() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("dataInizioSoggiorno"), filter.getDataA()));
            }

            if (filter.getAnnullata() != null) {
                predicates.add(cb.equal(root.get("annullata"), filter.getAnnullata()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
