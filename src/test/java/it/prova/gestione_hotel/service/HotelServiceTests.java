package it.prova.gestione_hotel.service;

import it.prova.gestione_hotel.dto.CameraDto;
import it.prova.gestione_hotel.dto.HotelDto;
import it.prova.gestione_hotel.exception.EntityNotFoundException;
import it.prova.gestione_hotel.model.TipoCamera;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class HotelServiceTests {
    @Autowired
    HotelService hotelService;

    @Test
    void getAll() {
        System.out.println("******** Inizio test getAll ********");
        int hotelIniziali = hotelService.getAll().size();
        HotelDto hotel = new HotelDto();
        hotel.setNome("Hotel Test");
        hotelService.addHotel(hotel);
        int hotelFinali = hotelService.getAll().size();

        Assertions.assertEquals(hotelIniziali + 1, hotelFinali);
        System.out.println("******** Fine test getAll ********");
    }

    @Test
    void findById() {
        System.out.println("******** Inizio test findById ********");
        HotelDto hotel = new HotelDto();
        hotel.setNome("Hotel Test");
        hotelService.addHotel(hotel);
        HotelDto hotelInserito = hotelService.getAll().stream().findFirst().orElse(null);

        HotelDto hotelTrovato = hotelService.findById(hotelInserito.getId());
        Assertions.assertNotNull(hotelTrovato);
        System.out.println("******** Fine test findById ********");
    }

    @Test
    void addHotel() {
        System.out.println("******** Inizio test addHotel ********");
        int hotelIniziali = hotelService.getAll().size();
        HotelDto hotel = new HotelDto();
        hotel.setNome("Hotel Test");
        hotelService.addHotel(hotel);
        int hotelFinali = hotelService.getAll().size();
        Assertions.assertEquals(hotelIniziali + 1, hotelFinali);
        System.out.println("******** Fine test addHotel ********");
    }

    @Test
    void modifyHotel() throws EntityNotFoundException {
        System.out.println("******** Inizio test modifyHotel ********");
        HotelDto hotel = new HotelDto();
        hotel.setNome("Hotel Test");
        hotelService.addHotel(hotel);
        HotelDto hotelDaModificare= hotelService.getAll().stream().findFirst().orElse(null);
        hotelDaModificare.setNome("Hotel modificato");
        hotelService.modifyHotel(hotelDaModificare);

        HotelDto hotelModificato = hotelService.getAll().stream().findFirst().orElse(null);

        Assertions.assertEquals(hotelModificato.getNome(), "Hotel modificato");

        hotelModificato.setId(100000L);
        Assertions.assertThrows(EntityNotFoundException.class,
                () -> hotelService.modifyHotel(hotelModificato));
        System.out.println("******** Fine test modifyHotel ********");
    }

    @Test
    void deleteHotel() throws EntityNotFoundException {
        System.out.println("******** Inizio test deleteHotel ********");
        int hotelIniziali = hotelService.getAll().size();
        HotelDto hotel = new HotelDto();
        hotel.setNome("Hotel Test");
        hotelService.addHotel(hotel);
        HotelDto hotelInserito = hotelService.getAll().stream().findFirst().orElse(null);
        hotelService.deleteHotel(hotelInserito.getId());
        int hotelFinali = hotelService.getAll().size();

        Assertions.assertEquals(hotelIniziali, hotelFinali);
        Assertions.assertThrows(EntityNotFoundException.class,
                () -> hotelService.deleteHotel(10L ));
        System.out.println("******** Fine test deleteHotel ********");
    }

}
