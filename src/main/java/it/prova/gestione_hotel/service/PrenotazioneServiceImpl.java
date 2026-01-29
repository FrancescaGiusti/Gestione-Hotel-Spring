package it.prova.gestione_hotel.service;

import it.prova.gestione_hotel.dto.PrenotazioneDto;
import it.prova.gestione_hotel.dto.PrenotazioneDtoFiltro;
import it.prova.gestione_hotel.exception.CameraNonTrovataException;
import it.prova.gestione_hotel.exception.InputNonValidoException;
import it.prova.gestione_hotel.exception.PrenotazioneNonTrovataException;
import it.prova.gestione_hotel.exception.UtenteNonTrovatoException;
import it.prova.gestione_hotel.model.Camera;
import it.prova.gestione_hotel.model.Prenotazione;
import it.prova.gestione_hotel.model.Utente;
import it.prova.gestione_hotel.repository.CameraRepository;
import it.prova.gestione_hotel.repository.PrenotazioneRepository;
import it.prova.gestione_hotel.repository.UtenteRepository;
import it.prova.gestione_hotel.specification.PrenotazioneSpecification;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class PrenotazioneServiceImpl implements PrenotazioneService{
    private final PrenotazioneRepository prenotazioneRepository;
    private final UtenteRepository utenteRepository;
    private final CameraRepository cameraRepository;

    @Autowired
    public PrenotazioneServiceImpl(PrenotazioneRepository prenotazioneRepository, UtenteRepository utenteRepository, CameraRepository cameraRepository) {
        this.prenotazioneRepository = prenotazioneRepository;
        this.utenteRepository = utenteRepository;
        this.cameraRepository = cameraRepository;
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
            throw new InputNonValidoException("Input non valido");

        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        Utente utente = utenteRepository.findByUsername(username);
        if (utente == null)
            throw new UtenteNonTrovatoException("Utente non trovato");

        if (prenotazioneDto.getCamera() == null ||
                prenotazioneDto.getCamera().getId() == null)
            throw new InputNonValidoException("Camera obbligatoria");

        Camera camera = cameraRepository.findById(
                prenotazioneDto.getCamera().getId()
        ).orElseThrow(() -> new CameraNonTrovataException("Camera non trovata"));

        LocalDate inizio = prenotazioneDto.getDataInizioSoggiorno();
        LocalDate fine = prenotazioneDto.getDataFineSoggiorno();

        if (inizio == null || fine == null)
            throw new InputNonValidoException("Date obbligatorie");

        if (!fine.isAfter(inizio))
            throw new InputNonValidoException(
                    "La data di fine deve essere successiva alla data di inizio"
            );

        boolean overlap = prenotazioneRepository
                .existsByCameraAndDataInizioSoggiornoLessThanEqualAndDataFineSoggiornoGreaterThanEqual(
                        camera, fine, inizio
                );

        if (overlap)
            throw new InputNonValidoException(
                    "La camera è già prenotata in questo intervallo"
            );

        Prenotazione prenotazione = new Prenotazione();
        prenotazione.setUtente(utente);
        prenotazione.setCamera(camera);
        prenotazione.setDataDiPrenotazione(LocalDate.now());
        prenotazione.setDataInizioSoggiorno(inizio);
        prenotazione.setDataFineSoggiorno(fine);
        prenotazione.setAnnullata(false);

        prenotazioneRepository.save(prenotazione);
    }


    @Override
    public void modifyReservation(PrenotazioneDto prenotazioneDto) throws PrenotazioneNonTrovataException {
        Prenotazione prenotazioneDaModificare = prenotazioneRepository.findById(prenotazioneDto.toModel().getId()).orElse(null);
        if(prenotazioneDaModificare == null)
            throw new PrenotazioneNonTrovataException("La prenotazione che vuoi modificare non esiste");
        prenotazioneRepository.save(prenotazioneDto.toModel());
    }

    @Override
    public void deleteReservation(Long id) {
        Prenotazione prenotazioneDaEliminare = prenotazioneRepository.findById(id).orElse(null);
        if(prenotazioneDaEliminare == null)
            throw new PrenotazioneNonTrovataException("La prenotazione che vuoi eliminare non esiste");
        prenotazioneRepository.delete(prenotazioneDaEliminare);
    }

    @Override
    public void logicDeleteReservation(Long id) throws PrenotazioneNonTrovataException {
        Prenotazione prenotazione = prenotazioneRepository.findById(id).orElse(null);
        if (prenotazione!= null){
            prenotazione.setAnnullata(true);
            prenotazioneRepository.save(prenotazione);
        } else{
            throw new PrenotazioneNonTrovataException("La prenotazione che vuoi eliminare non è presente");
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

    @Override
    public Set<PrenotazioneDto> filtraPrenotazioni(PrenotazioneDtoFiltro filter, Pageable pageable) {
        Specification<Prenotazione> spec = PrenotazioneSpecification.withFilter(filter);

        Page<Prenotazione> prenotazioniPaginate = prenotazioneRepository.findAll(spec, pageable);

        return prenotazioniPaginate.stream().map(p -> PrenotazioneDto.fromModel(p)).collect(Collectors.toSet());
    }
}
