package it.prova.gestione_hotel.service;

import it.prova.gestione_hotel.model.Hotel;
import it.prova.gestione_hotel.repository.HotelRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class HotelServiceImpl implements HotelService{
    private final HotelRepository hotelRepository;

    @Autowired
    public HotelServiceImpl(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Override
    public Set<Hotel> getAll() {
        return hotelRepository.findAll().stream().collect(Collectors.toSet());
    }

    @Override
    public Hotel findById(Long id) {
        Hotel hotelDaCercare = hotelRepository.findById(id).orElse(null);
        return hotelDaCercare;
    }

    @Override
    public void addHotel(Hotel hotel) {
        hotelRepository.save(hotel);
    }

    @Override
    public void modifyHotel(Hotel hotel) {
        Hotel hotelDaModificare = hotelRepository.findById(hotel.getId()).orElse(null);
        if (hotelDaModificare == null)
            throw new RuntimeException("L'hotel che vuoi modificare non esiste");
        hotelRepository.save(hotelDaModificare);
    }

    @Override
    public void deleteHotel(Long id) {
        Hotel hotelDaEliminare = hotelRepository.findById(id).orElse(null);
        if (hotelDaEliminare == null)
            throw new RuntimeException("L'hotel che vuoi eliminare non esiste");
        hotelRepository.save(hotelDaEliminare);
    }
}
