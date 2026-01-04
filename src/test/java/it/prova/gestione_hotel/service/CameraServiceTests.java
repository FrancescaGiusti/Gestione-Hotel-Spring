package it.prova.gestione_hotel.service;

import it.prova.gestione_hotel.dto.CameraDto;
import it.prova.gestione_hotel.dto.CameraPatchDto;
import it.prova.gestione_hotel.dto.HotelDto;
import it.prova.gestione_hotel.exception.EntityNotFoundException;
import it.prova.gestione_hotel.model.Camera;
import it.prova.gestione_hotel.model.TipoCamera;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class CameraServiceTests {
    @Autowired
    CameraService cameraService;

    @Autowired
    HotelService hotelService;

	@Test
	void getAll() {
        System.out.println("******** Inizio test getAll ********");
        HotelDto hotel = new HotelDto();
        hotel.setNome("Hotel Test");
        hotelService.addHotel(hotel);
        HotelDto hotelInserito = hotelService.getAll().stream().findFirst().orElse(null);

        int camereIniziali = cameraService.getAll().size();
        CameraDto camera = new CameraDto();
        camera.setTipoCamera(TipoCamera.MATRIMONIALE);
        camera.setNumeroCamera(13);
        camera.setPrezzoPerNotte(400.0);
        camera.setMaxOcppupanti(2);
        camera.setHotel(hotelInserito);
        cameraService.addRoom(camera);
        int camereFinali = cameraService.getAll().size();

        Assertions.assertEquals(camereIniziali + 1, camereFinali);
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

        CameraDto cameraTrovata = cameraService.findById(cameraAggiunta.getId());
        Assertions.assertNotNull(cameraTrovata);

        System.out.println("******** Fine test findById ********");
    }

    @Test
    void addRoom() {
        System.out.println("******** Inizio test addRoom ********");
        HotelDto hotel = new HotelDto();
        hotel.setNome("Hotel Test");
        hotelService.addHotel(hotel);
        HotelDto hotelInserito = hotelService.getAll().stream().findFirst().orElse(null);
        int camereIniziali = cameraService.getAll().size();
        CameraDto camera = new CameraDto();
        camera.setTipoCamera(TipoCamera.MATRIMONIALE);
        camera.setNumeroCamera(13);
        camera.setPrezzoPerNotte(400.0);
        camera.setMaxOcppupanti(2);
        camera.setHotel(hotelInserito);
        cameraService.addRoom(camera);
        int camereFinali = cameraService.getAll().size();

        Assertions.assertEquals(camereIniziali + 1, camereFinali);
        Assertions.assertThrows(RuntimeException.class,
                () -> cameraService.addRoom( null));
        System.out.println("******** Fine test addRoom ********");
    }

    @Test
    void modifyRoom() throws EntityNotFoundException {
        System.out.println("******** Inizio test modifyRoom ********");
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

        CameraDto cameraDaModificare = cameraService.getAll().stream().findFirst().orElse(null);

        cameraDaModificare.setTipoCamera(TipoCamera.SINGOLA);
        cameraService.modifyRoom(cameraDaModificare);

        CameraDto cameraModificata = cameraService.getAll().stream().findFirst().orElse(null);

        Assertions.assertEquals(cameraModificata.getTipoCamera(), TipoCamera.SINGOLA);

        cameraModificata.setId(100000L);
        Assertions.assertThrows(EntityNotFoundException.class,
                () -> cameraService.modifyRoom(cameraModificata));
        System.out.println("******** Fine test addRoom ********");
    }

    @Test
    void deleteRoom() throws EntityNotFoundException {
        System.out.println("******** Inizio test deleteRoom ********");
        HotelDto hotel = new HotelDto();
        hotel.setNome("Hotel Test");
        hotelService.addHotel(hotel);
        HotelDto hotelInserito = hotelService.getAll().stream().findFirst().orElse(null);
        int camereIniziali = cameraService.getAll().size();
        CameraDto camera = new CameraDto();
        camera.setTipoCamera(TipoCamera.MATRIMONIALE);
        camera.setNumeroCamera(13);
        camera.setPrezzoPerNotte(400.0);
        camera.setMaxOcppupanti(2);
        camera.setHotel(hotelInserito);
        cameraService.addRoom(camera);

        CameraDto cameraAggiunta = cameraService.getAll().stream().findFirst().orElse(null);
        Long id = cameraAggiunta.getId();
        cameraService.deleteRoom(id);
        int camereFinali = cameraService.getAll().size();

        Assertions.assertEquals(camereIniziali, camereFinali);
        Assertions.assertThrows(EntityNotFoundException.class,
                () -> cameraService.deleteRoom(10L ));
        System.out.println("******** Fine test deleteRoom ********");
    }

    @Test
    void modifyPartiallyRoom() throws EntityNotFoundException {
        System.out.println("******** Inizio test modifyPartiallyRoom ********");
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

        CameraDto cameraDaModificare = cameraService.getAll().stream().findFirst().orElse(null);
        Long id = cameraDaModificare.getId();
        CameraPatchDto cameraPatchDto = new CameraPatchDto(500.0);
        cameraService.modifyPartiallyCamera(id, cameraPatchDto);

        CameraDto cameraModificata = cameraService.getAll().stream().findFirst().orElse(null);

        Assertions.assertEquals(cameraModificata.getPrezzoPerNotte(), 500.0);

        Assertions.assertThrows(EntityNotFoundException.class,
                () -> cameraService.modifyPartiallyCamera(1000L, null));
        Assertions.assertThrows(RuntimeException.class,
                () -> cameraService.modifyPartiallyCamera(id, null));
        System.out.println("******** Fine test modifyPartiallyRoom ********");
    }

    @Test
    void findByTipoCamera() {
        System.out.println("******** Inizio test findByTipoCamera ********");
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

        CameraDto cameraMatrimoniale = cameraService.findByTipoCamera(TipoCamera.MATRIMONIALE).stream().findFirst().orElse(null);
        CameraDto cameraAggiunta = cameraService.getAll().stream().findFirst().orElse(null);

        Assertions.assertEquals(cameraMatrimoniale.getTipoCamera(), cameraAggiunta.getTipoCamera());
        Assertions.assertThrows(RuntimeException.class,
                () -> cameraService.findByTipoCamera(null));
        System.out.println("******** Fine test findByTipoCamera ********");
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

        CameraDto camera2 = new CameraDto();
        camera2.setTipoCamera(TipoCamera.SINGOLA);
        camera2.setNumeroCamera(30);
        camera2.setPrezzoPerNotte(100.0);
        camera2.setMaxOcppupanti(1);
        camera2.setHotel(hotelInserito);
        cameraService.addRoom(camera2);

        Pageable pageable = PageRequest.of(0, 2);
        int size = cameraService.findAllPageable(pageable).size();

        Assertions.assertEquals(2, size);

        pageable = PageRequest.of(0, 1);
        size = cameraService.findAllPageable(pageable).size();

        Assertions.assertEquals(1, size);
        System.out.println("******** Fine test findAllPageable ********");
    }




}
