package it.prova.gestione_hotel.service;

import it.prova.gestione_hotel.dto.HotelDto;
import it.prova.gestione_hotel.exception.EntityNotFoundException;
import it.prova.gestione_hotel.model.Hotel;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface HotelService {
    Set<HotelDto> getAll();
    HotelDto findById(Long id);
    void addHotel(HotelDto hotelDto);
    void modifyHotel(HotelDto hotelDto) throws EntityNotFoundException;
    void deleteHotel(Long id) throws EntityNotFoundException;
    Set<HotelDto> findByCitta(String citta);
    Set<HotelDto> getAllPageable(Pageable pageable);
}
