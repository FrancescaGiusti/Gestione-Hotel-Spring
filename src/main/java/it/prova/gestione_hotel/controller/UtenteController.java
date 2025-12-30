package it.prova.gestione_hotel.controller;

import it.prova.gestione_hotel.dto.UtenteAggiungiCreditoDto;
import it.prova.gestione_hotel.dto.UtenteDto;
import it.prova.gestione_hotel.exception.EntityNotFoundException;
import it.prova.gestione_hotel.service.UtenteService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Validated
@RestController
@RequestMapping("/api/utente")
public class UtenteController {
    private final UtenteService utenteService;


    public UtenteController(UtenteService utenteService) {
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
}
