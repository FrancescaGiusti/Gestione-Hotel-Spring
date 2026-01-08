package it.prova.gestione_hotel.controller;

import it.prova.gestione_hotel.dto.CameraDto;
import it.prova.gestione_hotel.dto.CameraDtoFiltro;
import it.prova.gestione_hotel.dto.CameraPatchDto;
import it.prova.gestione_hotel.exception.EntityNotFoundException;
import it.prova.gestione_hotel.model.TipoCamera;
import it.prova.gestione_hotel.service.CameraService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Validated
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
    public ResponseEntity<Void> addCamera(@Valid @RequestBody CameraDto cameraDto) {
        cameraService.addRoom(cameraDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity<Void> modifyCamera(@Valid @RequestBody CameraDto cameraDto) throws EntityNotFoundException {
        cameraService.modifyRoom(cameraDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCamera(@PathVariable Long id) throws EntityNotFoundException {
        cameraService.deleteRoom(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> modifyPartiallyCamera (@PathVariable Long id, @Valid @RequestBody CameraPatchDto cameraPatchDto) throws EntityNotFoundException {
        cameraService.modifyPartiallyCamera(id, cameraPatchDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping(params = "tipoCamera")
    public ResponseEntity<Set<CameraDto>> getCamereByTipoCamera(@RequestParam TipoCamera tipoCamera) {
        return ResponseEntity.status(HttpStatus.OK).body(cameraService.findByTipoCamera(tipoCamera));
    }

    @GetMapping("/page")
    public ResponseEntity<Set<CameraDto>> getCamerePaged(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(cameraService.findAllPageable(pageable));
    }

    @GetMapping("/filtro")
    public ResponseEntity<Set<CameraDto>> cercaCamereConFiltro(CameraDtoFiltro filter, Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(cameraService.findCameraWithFilter(filter, pageable));
    }


}
