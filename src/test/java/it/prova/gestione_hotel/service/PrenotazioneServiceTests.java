package it.prova.gestione_hotel.service;

import it.prova.gestione_hotel.dto.CameraDto;
import it.prova.gestione_hotel.dto.HotelDto;
import it.prova.gestione_hotel.dto.PrenotazioneDto;
import it.prova.gestione_hotel.dto.UtenteDto;
import it.prova.gestione_hotel.exception.EntityNotFoundException;
import it.prova.gestione_hotel.model.TipoCamera;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class PrenotazioneServiceTests {
    @Autowired
    CameraService cameraService;

    @Autowired
    HotelService hotelService;

    @Autowired
    UtenteService utenteService;

    @Autowired
    PrenotazioneService prenotazioneService;

    @Test
    void getAll() {
        System.out.println("******** Inizio test getAll ********");
        HotelDto hotel = new HotelDto();
        hotel.setNome("Hotel Test");
        hotelService.addHotel(hotel);
        HotelDto hotelInserito = hotelService.getAll().stream().findFirst().orElse(null);

        CameraDto camera = new CameraDto();
        camera.setTipoCamera(TipoCamera.MATRIMONIALE);
        camera.setNumeroCamera(13);
        camera.setPrezzoPerNotte(400.0);
        camera.setMaxOcppupanti(2);
        camera.setHotel(hotelInserito);
        cameraService.addRoom(camera);
        CameraDto cameraAggiunta = cameraService.getAll().stream().findFirst().orElse(null);

        UtenteDto utente = new UtenteDto();
        utente.setNome("Carlo");
        utente.setCognome("Rossi");
        utente.setCodiceFiscale("GHSR62536G");
        utenteService.addClient(utente);
        UtenteDto utenteAggiunto = utenteService.getAll().stream().findFirst().orElse(null);

        int prenotazioniIniziali = prenotazioneService.getAll().size();

        PrenotazioneDto prenotazione = new PrenotazioneDto();
        prenotazione.setDataDiPrenotazione(LocalDate.parse("2025-05-10"));
        prenotazione.setDataInizioSoggiorno(LocalDate.parse("2025-06-10"));
        prenotazione.setDataFineSoggiorno(LocalDate.parse("2025-06-20"));
        prenotazione.setCamera(cameraAggiunta);
        prenotazione.setUtente(utenteAggiunto);
        prenotazioneService.addReservation(prenotazione);

        int prenotazioniFinali = prenotazioneService.getAll().size();

        Assertions.assertEquals(prenotazioniIniziali + 1, prenotazioniFinali);
        System.out.println("******** Fine test getAll ********");
    }

    @Test
    void findById() {
        System.out.println("******** Inizio test findById ********");
        HotelDto hotel = new HotelDto();
        hotel.setNome("Hotel Test");
        hotelService.addHotel(hotel);
        HotelDto hotelInserito = hotelService.getAll().stream().findFirst().orElse(null);

        CameraDto camera = new CameraDto();
        camera.setTipoCamera(TipoCamera.MATRIMONIALE);
        camera.setNumeroCamera(13);
        camera.setPrezzoPerNotte(400.0);
        camera.setMaxOcppupanti(2);
        camera.setHotel(hotelInserito);
        cameraService.addRoom(camera);
        CameraDto cameraAggiunta = cameraService.getAll().stream().findFirst().orElse(null);

        UtenteDto utente = new UtenteDto();
        utente.setNome("Carlo");
        utente.setCognome("Rossi");
        utente.setCodiceFiscale("GHSR62536G");
        utenteService.addClient(utente);
        UtenteDto utenteAggiunto = utenteService.getAll().stream().findFirst().orElse(null);

        PrenotazioneDto prenotazione = new PrenotazioneDto();
        prenotazione.setDataDiPrenotazione(LocalDate.parse("2025-05-10"));
        prenotazione.setDataInizioSoggiorno(LocalDate.parse("2025-06-10"));
        prenotazione.setDataFineSoggiorno(LocalDate.parse("2025-06-20"));
        prenotazione.setCamera(cameraAggiunta);
        prenotazione.setUtente(utenteAggiunto);
        prenotazioneService.addReservation(prenotazione);
        PrenotazioneDto prenotazioneAggiunta = prenotazioneService.getAll().stream().findFirst().orElse(null);

        PrenotazioneDto prenotazioneTrovata = prenotazioneService.findById(prenotazioneAggiunta.getId());
        Assertions.assertNotNull(prenotazioneTrovata);
        System.out.println("******** Fine test findById ********");
    }

    @Test
    void addPrenotazione() {
        System.out.println("******** Inizio test addPrenotazione ********");
        HotelDto hotel = new HotelDto();
        hotel.setNome("Hotel Test");
        hotelService.addHotel(hotel);
        HotelDto hotelInserito = hotelService.getAll().stream().findFirst().orElse(null);

        CameraDto camera = new CameraDto();
        camera.setTipoCamera(TipoCamera.MATRIMONIALE);
        camera.setNumeroCamera(13);
        camera.setPrezzoPerNotte(400.0);
        camera.setMaxOcppupanti(2);
        camera.setHotel(hotelInserito);
        cameraService.addRoom(camera);
        CameraDto cameraAggiunta = cameraService.getAll().stream().findFirst().orElse(null);

        UtenteDto utente = new UtenteDto();
        utente.setNome("Carlo");
        utente.setCognome("Rossi");
        utente.setCodiceFiscale("GHSR62536G");
        utenteService.addClient(utente);
        UtenteDto utenteAggiunto = utenteService.getAll().stream().findFirst().orElse(null);

        int prenotazioniIniziali = prenotazioneService.getAll().size();

        PrenotazioneDto prenotazione = new PrenotazioneDto();
        prenotazione.setDataDiPrenotazione(LocalDate.parse("2025-05-10"));
        prenotazione.setDataInizioSoggiorno(LocalDate.parse("2025-06-10"));
        prenotazione.setDataFineSoggiorno(LocalDate.parse("2025-06-20"));
        prenotazione.setCamera(cameraAggiunta);
        prenotazione.setUtente(utenteAggiunto);
        prenotazioneService.addReservation(prenotazione);

        int prenotazioniFinali = prenotazioneService.getAll().size();

        Assertions.assertEquals(prenotazioniIniziali + 1, prenotazioniFinali);
        Assertions.assertThrows(RuntimeException.class,
                () -> prenotazioneService.addReservation( null));
        System.out.println("******** Fine test addPrenotazione ********");
    }

    @Test
    void modifyReservation() throws EntityNotFoundException {
        System.out.println("******** Inizio test modifyReservation ********");
        HotelDto hotel = new HotelDto();
        hotel.setNome("Hotel Test");
        hotelService.addHotel(hotel);
        HotelDto hotelInserito = hotelService.getAll().stream().findFirst().orElse(null);

        CameraDto camera = new CameraDto();
        camera.setTipoCamera(TipoCamera.MATRIMONIALE);
        camera.setNumeroCamera(13);
        camera.setPrezzoPerNotte(400.0);
        camera.setMaxOcppupanti(2);
        camera.setHotel(hotelInserito);
        cameraService.addRoom(camera);
        CameraDto cameraAggiunta = cameraService.getAll().stream().findFirst().orElse(null);

        UtenteDto utente = new UtenteDto();
        utente.setNome("Carlo");
        utente.setCognome("Rossi");
        utente.setCodiceFiscale("GHSR62536G");
        utenteService.addClient(utente);
        UtenteDto utenteAggiunto = utenteService.getAll().stream().findFirst().orElse(null);

        PrenotazioneDto prenotazione = new PrenotazioneDto();
        prenotazione.setDataDiPrenotazione(LocalDate.parse("2025-05-10"));
        prenotazione.setDataInizioSoggiorno(LocalDate.parse("2025-06-10"));
        prenotazione.setDataFineSoggiorno(LocalDate.parse("2025-06-20"));
        prenotazione.setCamera(cameraAggiunta);
        prenotazione.setUtente(utenteAggiunto);
        prenotazioneService.addReservation(prenotazione);


        PrenotazioneDto prenotazioneDaModificare = prenotazioneService.getAll().stream().findFirst().orElse(null);

        prenotazioneDaModificare.setAnnullata(true);
        prenotazioneService.modifyReservation(prenotazioneDaModificare);

        PrenotazioneDto prenotazioneModificata = prenotazioneService.getAll().stream().findFirst().orElse(null);

        Assertions.assertEquals(prenotazioneModificata.isAnnullata(), true);

        prenotazioneModificata.setId(100000L);
        Assertions.assertThrows(EntityNotFoundException.class,
                () -> prenotazioneService.modifyReservation(prenotazioneModificata));
        System.out.println("******** Fine test modifyReservation ********");
    }

    @Test
    void deleteReservation() throws EntityNotFoundException {
        System.out.println("******** Inizio test deleteReservation ********");

        HotelDto hotel = new HotelDto();
        hotel.setNome("Hotel Test");
        hotelService.addHotel(hotel);
        HotelDto hotelInserito = hotelService.getAll().stream().findFirst().orElse(null);

        CameraDto camera = new CameraDto();
        camera.setTipoCamera(TipoCamera.MATRIMONIALE);
        camera.setNumeroCamera(13);
        camera.setPrezzoPerNotte(400.0);
        camera.setMaxOcppupanti(2);
        camera.setHotel(hotelInserito);
        cameraService.addRoom(camera);
        CameraDto cameraAggiunta = cameraService.getAll().stream().findFirst().orElse(null);

        UtenteDto utente = new UtenteDto();
        utente.setNome("Carlo");
        utente.setCognome("Rossi");
        utente.setCodiceFiscale("GHSR62536G");
        utenteService.addClient(utente);
        UtenteDto utenteAggiunto = utenteService.getAll().stream().findFirst().orElse(null);

        int prenotazioniIniziali = prenotazioneService.getAll().size();

        PrenotazioneDto prenotazione = new PrenotazioneDto();
        prenotazione.setDataDiPrenotazione(LocalDate.parse("2025-05-10"));
        prenotazione.setDataInizioSoggiorno(LocalDate.parse("2025-06-10"));
        prenotazione.setDataFineSoggiorno(LocalDate.parse("2025-06-20"));
        prenotazione.setCamera(cameraAggiunta);
        prenotazione.setUtente(utenteAggiunto);
        prenotazioneService.addReservation(prenotazione);

        PrenotazioneDto prenotazioneAggiunta = prenotazioneService.getAll().stream().findFirst().orElse(null);
        prenotazioneService.deleteReservation(prenotazioneAggiunta.getId());
        int prenotazioniFinali = prenotazioneService.getAll().size();

        Assertions.assertEquals(prenotazioniIniziali, prenotazioniFinali);
        Assertions.assertThrows(RuntimeException.class,
                () -> prenotazioneService.deleteReservation( 10L));
        System.out.println("******** Fine test deleteReservation ********");
    }

    @Test
    void logicDeleteReservation() throws EntityNotFoundException {
        System.out.println("******** Inizio test logicDeleteReservation ********");
        HotelDto hotel = new HotelDto();
        hotel.setNome("Hotel Test");
        hotelService.addHotel(hotel);
        HotelDto hotelInserito = hotelService.getAll().stream().findFirst().orElse(null);

        CameraDto camera = new CameraDto();
        camera.setTipoCamera(TipoCamera.MATRIMONIALE);
        camera.setNumeroCamera(13);
        camera.setPrezzoPerNotte(400.0);
        camera.setMaxOcppupanti(2);
        camera.setHotel(hotelInserito);
        cameraService.addRoom(camera);
        CameraDto cameraAggiunta = cameraService.getAll().stream().findFirst().orElse(null);

        UtenteDto utente = new UtenteDto();
        utente.setNome("Carlo");
        utente.setCognome("Rossi");
        utente.setCodiceFiscale("GHSR62536G");
        utenteService.addClient(utente);
        UtenteDto utenteAggiunto = utenteService.getAll().stream().findFirst().orElse(null);

        PrenotazioneDto prenotazione = new PrenotazioneDto();
        prenotazione.setDataDiPrenotazione(LocalDate.parse("2025-05-10"));
        prenotazione.setDataInizioSoggiorno(LocalDate.parse("2025-06-10"));
        prenotazione.setDataFineSoggiorno(LocalDate.parse("2025-06-20"));
        prenotazione.setCamera(cameraAggiunta);
        prenotazione.setUtente(utenteAggiunto);
        prenotazioneService.addReservation(prenotazione);

        PrenotazioneDto prenotazioneAggiunta = prenotazioneService.getAll().stream().findFirst().orElse(null);
        prenotazioneService.logicDeleteReservation(prenotazioneAggiunta.getId());

        PrenotazioneDto prenotazioneCancellata = prenotazioneService.getAll().stream().findFirst().orElse(null);

        Assertions.assertEquals(prenotazioneCancellata.isAnnullata(), true);
        Assertions.assertThrows(EntityNotFoundException.class,
                () -> prenotazioneService.logicDeleteReservation( 10L));
        System.out.println("******** Fine test logicDeleteReservation ********");
    }

    @Test
    void findByAnnullataFalse() {
        System.out.println("******** Inizio test findByAnnullataFalse ********");
        HotelDto hotel = new HotelDto();
        hotel.setNome("Hotel Test");
        hotelService.addHotel(hotel);
        HotelDto hotelInserito = hotelService.getAll().stream().findFirst().orElse(null);

        CameraDto camera = new CameraDto();
        camera.setTipoCamera(TipoCamera.MATRIMONIALE);
        camera.setNumeroCamera(13);
        camera.setPrezzoPerNotte(400.0);
        camera.setMaxOcppupanti(2);
        camera.setHotel(hotelInserito);
        cameraService.addRoom(camera);
        CameraDto cameraAggiunta = cameraService.getAll().stream().findFirst().orElse(null);

        UtenteDto utente = new UtenteDto();
        utente.setNome("Carlo");
        utente.setCognome("Rossi");
        utente.setCodiceFiscale("GHSR62536G");
        utenteService.addClient(utente);
        UtenteDto utenteAggiunto = utenteService.getAll().stream().findFirst().orElse(null);

        PrenotazioneDto prenotazione = new PrenotazioneDto();
        prenotazione.setDataDiPrenotazione(LocalDate.parse("2025-05-10"));
        prenotazione.setDataInizioSoggiorno(LocalDate.parse("2025-06-10"));
        prenotazione.setDataFineSoggiorno(LocalDate.parse("2025-06-20"));
        prenotazione.setCamera(cameraAggiunta);
        prenotazione.setUtente(utenteAggiunto);
        prenotazioneService.addReservation(prenotazione);

        int prenotazioneAnnulataFalse = prenotazioneService.findByAnnullataFalse().size();
        Assertions.assertEquals(prenotazioneAnnulataFalse, 1);
        System.out.println("******** Fine test findByAnnullataFalse ********");
    }

    @Test
    void findAllPageable() {
        System.out.println("******** Inizio test findAllPageable ********");
        HotelDto hotel = new HotelDto();
        hotel.setNome("Hotel Test");
        hotelService.addHotel(hotel);
        HotelDto hotelInserito = hotelService.getAll().stream().findFirst().orElse(null);

        CameraDto camera = new CameraDto();
        camera.setTipoCamera(TipoCamera.MATRIMONIALE);
        camera.setNumeroCamera(13);
        camera.setPrezzoPerNotte(400.0);
        camera.setMaxOcppupanti(2);
        camera.setHotel(hotelInserito);
        cameraService.addRoom(camera);
        CameraDto cameraAggiunta = cameraService.getAll().stream().findFirst().orElse(null);

        UtenteDto utente = new UtenteDto();
        utente.setNome("Carlo");
        utente.setCognome("Rossi");
        utente.setCodiceFiscale("GHSR62536G");
        utenteService.addClient(utente);
        UtenteDto utenteAggiunto = utenteService.getAll().stream().findFirst().orElse(null);

        PrenotazioneDto prenotazione = new PrenotazioneDto();
        prenotazione.setDataDiPrenotazione(LocalDate.parse("2025-05-10"));
        prenotazione.setDataInizioSoggiorno(LocalDate.parse("2025-06-10"));
        prenotazione.setDataFineSoggiorno(LocalDate.parse("2025-06-20"));
        prenotazione.setCamera(cameraAggiunta);
        prenotazione.setUtente(utenteAggiunto);
        prenotazioneService.addReservation(prenotazione);

        PrenotazioneDto prenotazione2 = new PrenotazioneDto();
        prenotazione2.setDataDiPrenotazione(LocalDate.parse("2025-08-10"));
        prenotazione2.setDataInizioSoggiorno(LocalDate.parse("2025-09-10"));
        prenotazione2.setDataFineSoggiorno(LocalDate.parse("2025-09-20"));
        prenotazione2.setCamera(cameraAggiunta);
        prenotazione2.setUtente(utenteAggiunto);
        prenotazioneService.addReservation(prenotazione2);

        Pageable pageable = PageRequest.of(0, 2);
        int size = prenotazioneService.getAllPageable(pageable).size();

        Assertions.assertEquals(2, size);

        pageable = PageRequest.of(0, 1);
        size = prenotazioneService.getAllPageable(pageable).size();

        Assertions.assertEquals(1, size);
        System.out.println("******** Fine test findAllPageable ********");
    }

}
