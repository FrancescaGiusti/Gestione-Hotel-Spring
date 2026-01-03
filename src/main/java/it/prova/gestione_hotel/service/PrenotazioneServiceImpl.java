package it.prova.gestione_hotel.service;

import it.prova.gestione_hotel.dto.PrenotazioneDto;
import it.prova.gestione_hotel.exception.EntityNotFoundException;
import it.prova.gestione_hotel.model.Prenotazione;
import it.prova.gestione_hotel.repository.PrenotazioneRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Set<PrenotazioneDto> getAll() {
        return PrenotazioneDto.fromModel(prenotazioneRepository.findAll().stream().collect(Collectors.toSet()));
    }

    @Override
    public PrenotazioneDto findById(Long id) {
        if (prenotazioneRepository.findById(id).orElse(null) == null){
            return null;
        } else{
            return PrenotazioneDto.fromModel(prenotazioneRepository.findById(id).orElse(null));
        }
    }

    @Override
    public void addReservation(PrenotazioneDto prenotazioneDto) {
        if (prenotazioneDto == null)
            throw new RuntimeException("Input non valido");
        prenotazioneRepository.save(prenotazioneDto.toModel());
    }

    @Override
    public void modifyReservation(PrenotazioneDto prenotazioneDto) throws EntityNotFoundException {
        Prenotazione prenotazioneDaModificare = prenotazioneRepository.findById(prenotazioneDto.toModel().getId()).orElse(null);
        if(prenotazioneDaModificare == null)
            throw new EntityNotFoundException("La prenotazione che vuoi modificare non esiste");
        prenotazioneRepository.save(prenotazioneDto.toModel());
    }

    @Override
    public void deleteReservation(Long id) {
        Prenotazione prenotazioneDaEliminare = prenotazioneRepository.findById(id).orElse(null);
        if(prenotazioneDaEliminare == null)
            throw new RuntimeException("La prenotazione che vuoi eliminare non esiste");
        prenotazioneRepository.delete(prenotazioneDaEliminare);
    }

    @Override
    public void logicDeleteReservation(Long id) throws EntityNotFoundException {
        Prenotazione prenotazione = prenotazioneRepository.findById(id).orElse(null);
        if (prenotazione!= null){
            prenotazione.setAnnullata(true);
            prenotazioneRepository.save(prenotazione);
        } else{
            throw new EntityNotFoundException("La prenotazione che vuoi eliminare non è presente");
        }
    }

    @Override
    public Set<PrenotazioneDto> findByAnnullataFalse() {
        return PrenotazioneDto.fromModel(prenotazioneRepository.findByAnnullataFalse());
    }

    @Override
    public Set<PrenotazioneDto> getAllPageable(Pageable pageable) {
        Page<Prenotazione> prenotazionePaginated = prenotazioneRepository.findAll(pageable);
        return prenotazionePaginated.stream().map(p -> PrenotazioneDto.fromModel(p)).collect(Collectors.toSet());
    }
}
