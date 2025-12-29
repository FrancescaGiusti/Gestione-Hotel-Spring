package it.prova.gestione_hotel.controller;

import it.prova.gestione_hotel.dto.CameraDto;
import it.prova.gestione_hotel.dto.CameraPatchDto;
import it.prova.gestione_hotel.dto.PrenotazioneDto;
import it.prova.gestione_hotel.service.PrenotazioneService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

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
    public ResponseEntity<Void> addPrenotazione(@RequestBody PrenotazioneDto prenotazioneDto) {
        prenotazioneService.addReservation(prenotazioneDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity<Void> modifyPrenotazione(@RequestBody PrenotazioneDto prenotazioneDto) {
        prenotazioneService.modifyReservation(prenotazioneDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrenotazione(@PathVariable Long id) {
        prenotazioneService.deleteReservation(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
