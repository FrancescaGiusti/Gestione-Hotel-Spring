package it.prova.gestione_hotel.controller;

import it.prova.gestione_hotel.dto.*;
import it.prova.gestione_hotel.exception.EntityNotFoundException;
import it.prova.gestione_hotel.service.UtenteService;
import jakarta.validation.Valid;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Validated
@RestController
@RequestMapping("/api/utente")
public class UtenteController {
    private final UtenteService utenteService;

    public UtenteController(UtenteService utenteService, AuthenticationManager authenticationManager) {
        this.utenteService = utenteService;
    }

    @GetMapping
    public ResponseEntity<Set<UtenteDto>> getUtente() {
        return ResponseEntity.status(HttpStatus.OK).body(utenteService.getAll());
    }

    @PostMapping
    public ResponseEntity<Void> addUtente(@Valid @RequestBody UtenteDto utenteDto) {
        utenteService.addClient(utenteDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity<Void> modifyUtente(@RequestBody UtenteDto utenteDto) throws EntityNotFoundException {
        utenteService.modifyClient(utenteDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUtente(@PathVariable Long id) throws EntityNotFoundException {
        utenteService.deleteClient(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PatchMapping
    public ResponseEntity<Void> addCredit(@Valid @RequestBody UtenteAggiungiCreditoDto utenteAggiungiCreditoDto) throws EntityNotFoundException {
        utenteService.addCredito(utenteAggiungiCreditoDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/page")
    public ResponseEntity<Set<UtenteDto>> getAllPaginated(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(utenteService.getAllPaginated(pageable));
    }

    @GetMapping("/filtro")
    public ResponseEntity<Set<UtenteDto>> getAllFiltered(UtenteDtoFiltro filter, Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(utenteService.getAllFiltered(filter, pageable));
    }

    @GetMapping("/{username}")
    public ResponseEntity<UtenteDto> findByUsername(@PathVariable String username) {
        return ResponseEntity.status(HttpStatus.OK).body(utenteService.findByUsername(username));
    }

    @PostMapping("/{username}")
    public ResponseEntity<Void> addRoleToUser(@PathVariable String username,@Valid @RequestBody AggiungiRuoloDto richiesta)
            throws EntityNotFoundException {
        utenteService.addRolesToUser(username, richiesta.getCodice());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
