package it.prova.gestione_hotel.controller;

import it.prova.gestione_hotel.dto.CameraDto;
import it.prova.gestione_hotel.dto.CameraPatchDto;
import it.prova.gestione_hotel.dto.UtenteDto;
import it.prova.gestione_hotel.model.Camera;
import it.prova.gestione_hotel.service.CameraService;
import it.prova.gestione_hotel.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

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
    public ResponseEntity<Void> addUtente(@RequestBody UtenteDto utenteDto) {
        utenteService.addClient(utenteDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity<Void> modifyUtente(@RequestBody UtenteDto utenteDto) {
        utenteService.modifyClient(utenteDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUtente(@PathVariable Long id) {
        utenteService.deleteClient(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
