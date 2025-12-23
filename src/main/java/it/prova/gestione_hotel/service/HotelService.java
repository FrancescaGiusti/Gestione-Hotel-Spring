package it.prova.gestione_hotel.service;

import it.prova.gestione_hotel.model.Hotel;

import java.util.Set;

public interface HotelService {
    Set<Hotel> getAll();
    Hotel findById(Long id);
    void addHotel(Hotel hotel);
    void modifyHotel(Hotel hotel);
    void deleteHotel(Long id);
}
