package it.prova.gestione_hotel.controller;

import it.prova.gestione_hotel.dto.CameraDto;
import it.prova.gestione_hotel.dto.CameraPatchDto;
import it.prova.gestione_hotel.model.Camera;
import it.prova.gestione_hotel.service.CameraService;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/camera")
public class CameraController {
    private final CameraService cameraService;

    public CameraController(CameraService cameraService) {
        this.cameraService = cameraService;
    }

    @GetMapping
    public ResponseEntity<Set<CameraDto>> getCamere() {
        return ResponseEntity.status(HttpStatus.OK).body(cameraService.getAll());
    }

    @PostMapping
    public ResponseEntity<Void> addCamera(@RequestBody CameraDto cameraDto) {
        cameraService.addRoom(cameraDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity<Void> modifyCamera(@RequestBody CameraDto cameraDto) {
        cameraService.modifyRoom(cameraDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCorso(@PathVariable Long id) {
        cameraService.deleteRoom(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> modifyPartiallyCamera (@PathVariable Long id, @RequestBody CameraPatchDto cameraPatchDto) {
        cameraService.modifyPartiallyCamera(id, cameraPatchDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


}
