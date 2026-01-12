package it.prova.gestione_hotel.exception;

public class UsernameEsistenteException extends RuntimeException {
    public UsernameEsistenteException(String message) {
        super(message);
    }
}
