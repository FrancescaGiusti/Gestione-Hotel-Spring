package it.prova.gestione_hotel.service;

import it.prova.gestione_hotel.dto.RuoloDto;
import it.prova.gestione_hotel.exception.RuoloEsistenteException;
import it.prova.gestione_hotel.model.Codice;
import it.prova.gestione_hotel.model.Ruolo;
import it.prova.gestione_hotel.repository.RuoloRepository;
import org.springframework.stereotype.Service;

@Service
public class RuoloServiceImpl implements RuoloService {
    private final RuoloRepository ruoloRepository;

    public RuoloServiceImpl(RuoloRepository ruoloRepository) {
        this.ruoloRepository = ruoloRepository;
        populateDb();
    }

    @Override
    public void insertRole(RuoloDto ruoloDto) {
        if (ruoloRepository.findByCodice(ruoloDto.getCodice()) != null) {
            throw new RuoloEsistenteException("Il ruolo inserito già esiste");
        }
        ruoloRepository.save(ruoloDto.toModel());
    }

    private void populateDb() {
        for (Codice codice : Codice.values()) {
            if (ruoloRepository.findByCodice(codice) == null) {
                Ruolo ruolo = new Ruolo(
                        codice,
                        "Ruolo " + codice.name()
                );
                ruoloRepository.save(ruolo);
            }
        }
    }
}
