package it.prova.gestione_hotel.dto;

import jakarta.validation.constraints.NotBlank;

public class HotelDtoFiltro {
    @NotBlank
    private String citta;

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }
}
