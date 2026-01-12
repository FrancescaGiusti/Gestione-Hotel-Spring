package it.prova.gestione_hotel.service;

import it.prova.gestione_hotel.dto.UtenteAggiungiCreditoDto;
import it.prova.gestione_hotel.dto.UtenteDto;
import it.prova.gestione_hotel.dto.UtenteDtoFiltro;
import it.prova.gestione_hotel.exception.EntityNotFoundException;
import it.prova.gestione_hotel.exception.InputNonValidoException;
import it.prova.gestione_hotel.exception.UsernameEsistenteException;
import it.prova.gestione_hotel.exception.UtenteNonTrovatoException;
import it.prova.gestione_hotel.model.Codice;
import it.prova.gestione_hotel.model.Ruolo;
import it.prova.gestione_hotel.model.Utente;
import it.prova.gestione_hotel.repository.RuoloRepository;
import it.prova.gestione_hotel.repository.UtenteRepository;
import it.prova.gestione_hotel.specification.UtenteSpecification;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class UtenteServiceImpl implements UtenteService{
    private final UtenteRepository utenteRepository;
    private final PasswordEncoder passwordEncoder;
    private final RuoloRepository ruoloRepository;

    @Autowired
    public UtenteServiceImpl(UtenteRepository utenteRepository, PasswordEncoder passwordEncoder, RuoloRepository ruoloRepository) {
        this.utenteRepository = utenteRepository;
        this.passwordEncoder = passwordEncoder;
        this.ruoloRepository = ruoloRepository;
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
            throw new InputNonValidoException("Input non valido");
        Utente utente = utenteDto.toModel();
        if (utenteRepository.findByUsername(utente.getUsername()) != null) {
            throw new UsernameEsistenteException("Lo username già esiste");
        }
        utente.setPassword(passwordEncoder.encode(utente.getPassword()));
        utenteRepository.save(utente);
    }

    @Override
    public void modifyClient(UtenteDto utenteDto) throws UtenteNonTrovatoException {
        Utente daModificare = utenteRepository.findById(utenteDto.toModel().getId()).orElse(null);
        if (daModificare == null)
            throw new UtenteNonTrovatoException("l'utente che vuoi modificare non esiste");
        Utente utente = utenteDto.toModel();
        if(utente.getPassword() != null || !utente.getPassword().isBlank()){
            utente.setPassword(passwordEncoder.encode(utente.getPassword()));
        }
        utenteRepository.save(utente);
    }

    @Override
    public void deleteClient(Long id) throws UtenteNonTrovatoException {
        Utente daEliminare = utenteRepository.findById(id).orElse(null);
        if (daEliminare == null)
            throw new UtenteNonTrovatoException("l'utente che vuoi eliminare non esiste");
        utenteRepository.delete(daEliminare);
    }

    @Override
    public void addCredito(UtenteAggiungiCreditoDto utenteAggiungiCreditoDto) throws UtenteNonTrovatoException {
//        if (utenteAggiungiCreditoDto.getId() == null && utenteAggiungiCreditoDto.getCreditoDaAggiungere() == null)
//            throw new RuntimeException("Input non valido");
//        int updated = utenteRepository.addCredit(utenteAggiungiCreditoDto.getId(), utenteAggiungiCreditoDto.getCreditoDaAggiungere());
//        if (updated == 0) {
//            throw new EntityNotFoundException("Entità non trovata, la modifica non è avvenuta correttamente");
//        }

        Utente utente = utenteRepository.findById(utenteAggiungiCreditoDto.getId())
                .orElseThrow(() -> new UtenteNonTrovatoException("Utente non trovato"));

        utente.setCreditoDisponibile(
                utente.getCreditoDisponibile() + utenteAggiungiCreditoDto.getCreditoDaAggiungere()
        );
    }

    @Override
    public Set<UtenteDto> getAllPaginated(Pageable pageable) {
        Page<Utente> utentePaginated = utenteRepository.findAll(pageable);
        return utentePaginated.stream().map(u -> UtenteDto.fromModel(u)).collect(Collectors.toSet());
    }

    @Override
    public Set<UtenteDto> getAllFiltered(UtenteDtoFiltro filter, Pageable pageable) {
        Specification<Utente> spec = UtenteSpecification.searchWithFilter(filter);
        Page<Utente> utenti = utenteRepository.findAll(spec, pageable);
        return utenti.stream().map(u -> UtenteDto.fromModel(u)).collect(Collectors.toSet());
    }

    @Override
    public void addRolesToUser(String username, Codice codiceRuolo) throws EntityNotFoundException {
        Utente utente = utenteRepository.findByUsername(username);
        if(utente == null){
            throw new UtenteNonTrovatoException("Utente non trovato");
        }
        Ruolo ruolo = ruoloRepository.findByCodice(codiceRuolo).stream().findFirst().orElse(null);
        if(ruolo == null){
            throw new EntityNotFoundException("Ruolo non trovato");
        }
        utente.getRuoli().add(ruolo);
    }

    @Override
    public UtenteDto findByUsername(String username) {
       Utente utente = utenteRepository.findByUsername(username);
        if(utente == null){
            throw new UtenteNonTrovatoException("Utente non trovato");
        }
       return UtenteDto.fromModel(utente);
    }


}
