package it.prova.gestione_hotel.dto;

import jakarta.validation.constraints.NotBlank;

public class HotelDtoFiltro {
    private String citta;
    private String nome;

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
