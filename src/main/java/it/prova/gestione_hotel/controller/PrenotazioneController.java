package it.prova.gestione_hotel.controller;

import it.prova.gestione_hotel.dto.CameraDto;
import it.prova.gestione_hotel.dto.CameraPatchDto;
import it.prova.gestione_hotel.dto.PrenotazioneDto;
import it.prova.gestione_hotel.exception.EntityNotFoundException;
import it.prova.gestione_hotel.service.PrenotazioneService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Validated
@RestController
@RequestMapping("/api/prenotazione")
public class PrenotazioneController {
    private final PrenotazioneService prenotazioneService;

    public PrenotazioneController(PrenotazioneService prenotazioneService) {
        this.prenotazioneService = prenotazioneService;
    }

    @GetMapping
    public ResponseEntity<Set<PrenotazioneDto>> getPrenotazione() {
        return ResponseEntity.status(HttpStatus.OK).body(prenotazioneService.getAll());
    }

    @PostMapping
    public ResponseEntity<Void> addPrenotazione(@Valid @RequestBody PrenotazioneDto prenotazioneDto) {
        prenotazioneService.addReservation(prenotazioneDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity<Void> modifyPrenotazione(@Valid @RequestBody PrenotazioneDto prenotazioneDto) throws EntityNotFoundException {
        prenotazioneService.modifyReservation(prenotazioneDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrenotazione(@PathVariable Long id) {
        prenotazioneService.deleteReservation(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> logicDeletePrenotazione(@PathVariable Long id) throws EntityNotFoundException {
        prenotazioneService.logicDeleteReservation(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/attive")
    public ResponseEntity<Set<PrenotazioneDto>> getPrenotazioneAttive() {
        return ResponseEntity.status(HttpStatus.OK).body(prenotazioneService.findByAnnullataFalse());
    }

    @GetMapping("/page")
    public ResponseEntity<Set<PrenotazioneDto>> getPrenotazioniPageable(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(prenotazioneService.getAllPageable(pageable));
    }

}
