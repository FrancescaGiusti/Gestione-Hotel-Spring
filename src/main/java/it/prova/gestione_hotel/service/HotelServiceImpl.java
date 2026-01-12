package it.prova.gestione_hotel.service;

import it.prova.gestione_hotel.dto.HotelDto;
import it.prova.gestione_hotel.dto.HotelDtoFiltro;
import it.prova.gestione_hotel.exception.HotelNonTrovatoException;
import it.prova.gestione_hotel.exception.InputNonValidoException;
import it.prova.gestione_hotel.model.Hotel;
import it.prova.gestione_hotel.repository.HotelRepository;
import it.prova.gestione_hotel.specification.HotelSpecification;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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
    public Set<HotelDto> getAll() {
        return HotelDto.fromModel(hotelRepository.findAll().stream().collect(Collectors.toSet()));
    }

    @Override
    public HotelDto findById(Long id) {
        Hotel hotelDaCercare = hotelRepository.findById(id).orElse(null);
        if (hotelDaCercare == null){
            return null;
        } else {
            return HotelDto.fromModel(hotelDaCercare);
        }
    }

    @Override
    public void addHotel(HotelDto hotelDto) {
        hotelRepository.save(hotelDto.toModel());
    }

    @Override
    public void modifyHotel(HotelDto hotelDto) throws HotelNonTrovatoException {
        Hotel hotelDaModificare = hotelRepository.findById(hotelDto.toModel().getId()).orElse(null);
        if (hotelDaModificare == null)
            throw new HotelNonTrovatoException("L'hotel che vuoi modificare non esiste");
        hotelRepository.save(hotelDto.toModel());
    }

    @Override
    public void deleteHotel(Long id) throws HotelNonTrovatoException {
        Hotel hotelDaEliminare = hotelRepository.findById(id).orElse(null);
        if (hotelDaEliminare == null)
            throw new HotelNonTrovatoException("L'hotel che vuoi eliminare non esiste");
        hotelRepository.delete(hotelDaEliminare);
    }

    @Override
    public Set<HotelDto> findByCitta(String citta) {
        if(citta == null)
            throw new InputNonValidoException("Input non valido");
        return HotelDto.fromModel(hotelRepository.findByCitta(citta));
    }

    @Override
    public Set<HotelDto> getAllPageable(Pageable pageable) {
        Page<Hotel> hotelPaginated = hotelRepository.findAll(pageable);
        return hotelPaginated.stream().map(h -> HotelDto.fromModel(h)).collect(Collectors.toSet());
    }

    @Override
    public Set<HotelDto> getAllFiltered(HotelDtoFiltro filter, Pageable pageable) {
        Specification<Hotel> spec = HotelSpecification.searchWithFilter(filter);

        Page<Hotel> hotelPaginati = hotelRepository.findAll(spec, pageable);
        return hotelPaginati.stream().map(m -> HotelDto.fromModel(m)).collect(Collectors.toSet());
    }
}
