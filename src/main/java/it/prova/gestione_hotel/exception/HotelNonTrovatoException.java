package it.prova.gestione_hotel.exception;

public class HotelNonTrovatoException extends RuntimeException {
    public HotelNonTrovatoException(String message) {
        super(message);
    }
}
