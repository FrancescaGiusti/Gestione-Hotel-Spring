package it.prova.gestione_hotel.controller;

import it.prova.gestione_hotel.dto.RuoloDto;
import it.prova.gestione_hotel.service.RuoloService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/api/ruolo")
public class RuoloController {
    private final RuoloService ruoloService;

    public RuoloController(RuoloService ruoloService) {
        this.ruoloService = ruoloService;
    }

    @PostMapping
    public ResponseEntity<Void> addRole(@Valid @RequestBody RuoloDto ruoloDto) {
        ruoloService.insertRole(ruoloDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
