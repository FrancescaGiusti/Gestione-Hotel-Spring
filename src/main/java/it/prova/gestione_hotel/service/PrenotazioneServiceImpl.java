package it.prova.gestione_hotel.service;

import it.prova.gestione_hotel.model.Prenotazione;
import it.prova.gestione_hotel.repository.PrenotazioneRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class PrenotazioneServiceImpl implements PrenotazioneService{
    private final PrenotazioneRepository prenotazioneRepository;

    @Autowired
    public PrenotazioneServiceImpl(PrenotazioneRepository prenotazioneRepository) {
        this.prenotazioneRepository = prenotazioneRepository;
    }

    @Override
    public Set<Prenotazione> getAll() {
        return prenotazioneRepository.findAll().stream().collect(Collectors.toSet());
    }

    @Override
    public Prenotazione findById(Long id) {
        return prenotazioneRepository.findById(id).orElse(null);
    }

    @Override
    public void addReservation(Prenotazione prenotazione) {
        if (prenotazione == null)
            throw new RuntimeException("Input non valido");
        prenotazioneRepository.save(prenotazione);
    }

    @Override
    public void modifyReservation(Prenotazione prenotazione) {
        Prenotazione prenotazioneDaModificare = prenotazioneRepository.findById(prenotazione.getId()).orElse(null);
        if(prenotazioneDaModificare == null)
            throw new RuntimeException("La prenotazione che vuoi modificare non esiste");
        prenotazioneRepository.save(prenotazioneDaModificare);
    }

    @Override
    public void deleteReservation(Long id) {
        Prenotazione prenotazioneDaEliminare = prenotazioneRepository.findById(id).orElse(null);
        if(prenotazioneDaEliminare == null)
            throw new RuntimeException("La prenotazione che vuoi eliminare non esiste");
        prenotazioneRepository.save(prenotazioneDaEliminare);
    }

    @Override
    public void logicDeleteReservation(Long id) {
        Prenotazione prenotazione = prenotazioneRepository.findById(id).orElse(null);
        if (prenotazione!= null){
            prenotazione.setAnnullata(true);
        } else{
            throw new RuntimeException("La prenotazione che vuoi eliminare non è presente");
        }
    }
}
