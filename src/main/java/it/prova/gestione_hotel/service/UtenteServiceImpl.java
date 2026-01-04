package it.prova.gestione_hotel.service;

import it.prova.gestione_hotel.dto.UtenteAggiungiCreditoDto;
import it.prova.gestione_hotel.dto.UtenteDto;
import it.prova.gestione_hotel.exception.EntityNotFoundException;
import it.prova.gestione_hotel.model.Utente;
import it.prova.gestione_hotel.repository.UtenteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class UtenteServiceImpl implements UtenteService{
    private final UtenteRepository utenteRepository;

    @Autowired
    public UtenteServiceImpl(UtenteRepository utenteRepository) {
        this.utenteRepository = utenteRepository;
    }

    @Override
    public Set<UtenteDto> getAll() {
        return UtenteDto.fromModel(utenteRepository.findAll().stream().collect(Collectors.toSet()));
    }

    @Override
    public UtenteDto findById(Long id) {
        if (utenteRepository.findById(id).orElse(null) == null){
            return null;
        } else {
            return UtenteDto.fromModelLight(utenteRepository.findById(id).orElse(null));
        }
    }

    @Override
    public void addClient(UtenteDto utenteDto) {
        if (utenteDto == null)
            throw new RuntimeException("Input non valido");
        utenteRepository.save(utenteDto.toModel());
    }

    @Override
    public void modifyClient(UtenteDto utenteDto) throws EntityNotFoundException {
        Utente daModificare = utenteRepository.findById(utenteDto.toModel().getId()).orElse(null);
        if (daModificare == null)
            throw new EntityNotFoundException("l'utente che vuoi modificare non esiste");
        utenteRepository.save(utenteDto.toModel());
    }

    @Override
    public void deleteClient(Long id) throws EntityNotFoundException {
        Utente daEliminare = utenteRepository.findById(id).orElse(null);
        if (daEliminare == null)
            throw new EntityNotFoundException("l'utente che vuoi eliminare non esiste");
        utenteRepository.delete(daEliminare);
    }

    @Override
    public void addCredito(UtenteAggiungiCreditoDto utenteAggiungiCreditoDto) throws EntityNotFoundException {
//        if (utenteAggiungiCreditoDto.getId() == null && utenteAggiungiCreditoDto.getCreditoDaAggiungere() == null)
//            throw new RuntimeException("Input non valido");
//        int updated = utenteRepository.addCredit(utenteAggiungiCreditoDto.getId(), utenteAggiungiCreditoDto.getCreditoDaAggiungere());
//        if (updated == 0) {
//            throw new EntityNotFoundException("Entità non trovata, la modifica non è avvenuta correttamente");
//        }

        Utente utente = utenteRepository.findById(utenteAggiungiCreditoDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Utente non trovato"));

        utente.setCreditoDisponibile(
                utente.getCreditoDisponibile() + utenteAggiungiCreditoDto.getCreditoDaAggiungere()
        );
    }

    @Override
    public Set<UtenteDto> getAllPaginated(Pageable pageable) {
        Page<Utente> utentePaginated = utenteRepository.findAll(pageable);
        return utentePaginated.stream().map(u -> UtenteDto.fromModel(u)).collect(Collectors.toSet());
    }


}
