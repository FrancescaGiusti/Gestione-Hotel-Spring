package it.prova.gestione_hotel.specification;

import it.prova.gestione_hotel.dto.CameraDtoFiltro;
import it.prova.gestione_hotel.model.Camera;
import it.prova.gestione_hotel.model.Prenotazione;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class CameraSpecification {
    public static Specification<Camera> withPrenotazioniBetweenDates(CameraDtoFiltro filter) {
        return (root, query, cb) -> {

            query.distinct(true);

            Join<Camera, Prenotazione> prenotazioneJoin =
                    root.join("prenotazioni", JoinType.INNER);

            List<Predicate> predicates = new ArrayList<>();

            predicates.add(
                    cb.isFalse(prenotazioneJoin.get("annullata"))
            );

            if (filter.getDataInizioDa() != null && filter.getDataFineA() != null) {
                predicates.add(
                        cb.lessThanOrEqualTo(prenotazioneJoin.get("dataInizioSoggiorno"), filter.getDataFineA())
                );

                predicates.add(
                        cb.greaterThanOrEqualTo(prenotazioneJoin.get("dataFineSoggiorno"), filter.getDataInizioDa())
                );
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };

    }
}
