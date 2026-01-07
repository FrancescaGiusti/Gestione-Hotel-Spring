package it.prova.gestione_hotel.controller;

import it.prova.gestione_hotel.dto.HotelDto;
import it.prova.gestione_hotel.dto.HotelDtoFiltro;
import it.prova.gestione_hotel.exception.EntityNotFoundException;
import it.prova.gestione_hotel.service.HotelService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Validated
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
    public ResponseEntity<Void> addHotel(@Valid @RequestBody HotelDto hotelDto) {
        hotelService.addHotel(hotelDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity<Void> modifyHotel(@RequestBody HotelDto hotelDto) throws EntityNotFoundException {
        hotelService.modifyHotel(hotelDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHotel(@PathVariable Long id) throws EntityNotFoundException {
        hotelService.deleteHotel(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping(params = "citta")
    public ResponseEntity<Set<HotelDto>> findByCity(@RequestParam String citta) {
       return ResponseEntity.status(HttpStatus.OK).body(hotelService.findByCitta(citta));
    }

    @GetMapping("/page")
    public ResponseEntity<Set<HotelDto>> getAllPageable(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(hotelService.getAllPageable(pageable));
    }

    @GetMapping("/filtro")
    public ResponseEntity<Set<HotelDto>> getAllFiltered(HotelDtoFiltro filter, Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(hotelService.getAllFiltered(filter, pageable));
    }
}
