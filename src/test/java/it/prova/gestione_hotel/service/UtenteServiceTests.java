package it.prova.gestione_hotel.service;

import it.prova.gestione_hotel.dto.HotelDto;
import it.prova.gestione_hotel.dto.UtenteAggiungiCreditoDto;
import it.prova.gestione_hotel.dto.UtenteDto;
import it.prova.gestione_hotel.exception.EntityNotFoundException;
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
    void deleteUtente() throws EntityNotFoundException {
        System.out.println("******** Inizio test deleteUtente ********");
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
        System.out.println("******** Fine test deleteUtente ********");
    }

    @Test
    void addCredito() throws EntityNotFoundException {
        System.out.println("******** Inizio test addCredito ********");
        UtenteDto utente = new UtenteDto();
        utente.setNome("Carlo");
        utente.setCognome("Rossi");
        utente.setCodiceFiscale("GHSR62536G");
        utenteService.addClient(utente);
        UtenteDto utenteAggiunto = utenteService.getAll().stream().findFirst().orElse(null);

        UtenteAggiungiCreditoDto utenteACuiAggiungereIlCredito = new UtenteAggiungiCreditoDto();
        utenteACuiAggiungereIlCredito.setCreditoDaAggiungere(300.0);
        utenteACuiAggiungereIlCredito.setId(utenteAggiunto.getId());
        utenteService.addCredito(utenteACuiAggiungereIlCredito);

        UtenteDto utenteConCreditoModificato = utenteService.getAll().stream().findFirst().orElse(null);
        Assertions.assertEquals(utenteConCreditoModificato.getCreditoDisponibile(), 300.0);

        UtenteAggiungiCreditoDto utentePerVerificaEccezione = new UtenteAggiungiCreditoDto();
        utentePerVerificaEccezione.setCreditoDaAggiungere(300.0);
        utentePerVerificaEccezione.setId(1000L);
        Assertions.assertThrows(EntityNotFoundException.class,
                ()-> utenteService.addCredito(utentePerVerificaEccezione));
        System.out.println("******** Fine test addCredito ********");
    }

    @Test
    void findAllPageable() {
        System.out.println("******** Inizio test findAllPageable ********");
        UtenteDto utente = new UtenteDto();
        utente.setNome("Carlo");
        utente.setCognome("Rossi");
        utente.setCodiceFiscale("GHSR62536G");
        utenteService.addClient(utente);

        UtenteDto utente2 = new UtenteDto();
        utente2.setNome("Mario");
        utente2.setCognome("Rossi");
        utente2.setCodiceFiscale("MRSR62536G");
        utenteService.addClient(utente2);

        Pageable pageable = PageRequest.of(0, 2);
        int size = utenteService.getAllPaginated(pageable).size();

        Assertions.assertEquals(2, size);

        pageable = PageRequest.of(0, 1);
        size = utenteService.getAllPaginated(pageable).size();

        Assertions.assertEquals(1, size);
        System.out.println("******** Fine test findAllPageable ********");
    }
}
