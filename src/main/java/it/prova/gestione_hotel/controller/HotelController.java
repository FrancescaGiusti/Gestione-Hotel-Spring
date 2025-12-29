package it.prova.gestione_hotel.controller;

import it.prova.gestione_hotel.dto.CameraDto;
import it.prova.gestione_hotel.dto.CameraPatchDto;
import it.prova.gestione_hotel.dto.HotelDto;
import it.prova.gestione_hotel.service.HotelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/hotel")
public class HotelController {
    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping
    public ResponseEntity<Set<HotelDto>> getHotel() {
        return ResponseEntity.status(HttpStatus.OK).body(hotelService.getAll());
    }

    @PostMapping
    public ResponseEntity<Void> addHotel(@RequestBody HotelDto hotelDto) {
        hotelService.addHotel(hotelDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity<Void> modifyHotel(@RequestBody HotelDto hotelDto) {
        hotelService.modifyHotel(hotelDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHotel(@PathVariable Long id) {
        hotelService.deleteHotel(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
