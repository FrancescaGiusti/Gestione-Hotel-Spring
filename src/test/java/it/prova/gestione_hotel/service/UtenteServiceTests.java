package it.prova.gestione_hotel.service;

import it.prova.gestione_hotel.dto.HotelDto;
import it.prova.gestione_hotel.dto.UtenteDto;
import it.prova.gestione_hotel.exception.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class UtenteServiceTests {
    @Autowired
    UtenteService utenteService;

    @Test
    void getAll() {
        System.out.println("******** Inizio test getAll ********");
        int utentiIniziali = utenteService.getAll().size();
        UtenteDto utente = new UtenteDto();
        utente.setNome("Carlo");
        utente.setCognome("Rossi");
        utente.setCodiceFiscale("GHSR62536G");
        utenteService.addClient(utente);
        UtenteDto utenteAggiunto = utenteService.getAll().stream().findFirst().orElse(null);
        int utentiFinali = utenteService.getAll().size();

        Assertions.assertEquals(utentiIniziali + 1, utentiFinali);
        System.out.println("******** Fine test getAll ********");
    }

    @Test
    void findById() {
        System.out.println("******** Inizio test findById ********");
        UtenteDto utente = new UtenteDto();
        utente.setNome("Carlo");
        utente.setCognome("Rossi");
        utente.setCodiceFiscale("GHSR62536G");
        utenteService.addClient(utente);
        UtenteDto utenteAggiunto = utenteService.getAll().stream().findFirst().orElse(null);

        UtenteDto utenteTrovato = utenteService.findById(utenteAggiunto.getId());
        Assertions.assertNotNull(utenteTrovato);
        System.out.println("******** Fine test findById ********");
    }

    @Test
    void addUtente() {
        System.out.println("******** Inizio test addUtente ********");
        int utentiIniziali = utenteService.getAll().size();
        UtenteDto utente = new UtenteDto();
        utente.setNome("Carlo");
        utente.setCognome("Rossi");
        utente.setCodiceFiscale("GHSR62536G");
        utenteService.addClient(utente);
        int utentiFinali = utenteService.getAll().size();
        Assertions.assertEquals(utentiIniziali + 1, utentiFinali);
        Assertions.assertThrows(RuntimeException.class,
                () -> utenteService.addClient(null));
        System.out.println("******** Fine test addUtente ********");
    }

    @Test
    void modifyUtente() throws EntityNotFoundException {
        System.out.println("******** Inizio test modifyUtente ********");
        UtenteDto utente = new UtenteDto();
        utente.setNome("Carlo");
        utente.setCognome("Rossi");
        utente.setCodiceFiscale("GHSR62536G");
        utenteService.addClient(utente);
        UtenteDto utenteDaModificare = utenteService.getAll().stream().findFirst().orElse(null);
        utenteDaModificare.setNome("Chiara");
        utenteService.modifyClient(utenteDaModificare);

        UtenteDto utenteModificato = utenteService.getAll().stream().findFirst().orElse(null);

        Assertions.assertEquals(utenteModificato.getNome(), "Chiara");

        utenteModificato.setId(100000L);
        Assertions.assertThrows(EntityNotFoundException.class,
                () -> utenteService.modifyClient(utenteModificato));
        System.out.println("******** Fine test modifyUtente ********");
    }

    @Test
    void deleteHotel() throws EntityNotFoundException {
        System.out.println("******** Inizio test deleteHotel ********");
        int utentiIniziali = utenteService.getAll().size();
        UtenteDto utente = new UtenteDto();
        utente.setNome("Carlo");
        utente.setCognome("Rossi");
        utente.setCodiceFiscale("GHSR62536G");
        utenteService.addClient(utente);
        UtenteDto utenteAggiunto = utenteService.getAll().stream().findFirst().orElse(null);
        utenteService.deleteClient(utenteAggiunto.getId());
        int utentiFinali = utenteService.getAll().size();

        Assertions.assertEquals(utentiIniziali, utentiFinali);
        Assertions.assertThrows(EntityNotFoundException.class,
                () -> utenteService.deleteClient(10L ));
        System.out.println("******** Fine test deleteHotel ********");
    }

    @Test
    void addCredito() {
        System.out.println("******** Inizio test addCredito ********");
        System.out.println("******** Fine test addCredito ********");
    }

    @Test
    void findAllPageable() {
        System.out.println("******** Inizio test findAllPageable ********");
        System.out.println("******** Fine test findAllPageable ********");
    }
}
