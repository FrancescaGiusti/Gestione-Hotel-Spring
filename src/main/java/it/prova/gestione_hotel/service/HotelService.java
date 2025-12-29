package it.prova.gestione_hotel.service;

import it.prova.gestione_hotel.dto.HotelDto;
import it.prova.gestione_hotel.model.Hotel;

import java.util.Set;

public interface HotelService {
    Set<HotelDto> getAll();
    HotelDto findById(Long id);
    void addHotel(HotelDto hotelDto);
    void modifyHotel(HotelDto hotelDto);
    void deleteHotel(Long id);
}
