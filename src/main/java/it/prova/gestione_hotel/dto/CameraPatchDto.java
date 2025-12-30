package it.prova.gestione_hotel.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class CameraPatchDto {
    @NotBlank
    @Positive
    private Double prezzoPerNotte;

    public CameraPatchDto() {

    }

    public CameraPatchDto(Double prezzoPerNotte) {
        this.prezzoPerNotte = prezzoPerNotte;
    }

    public Double getPrezzoPerNotte() {
        return prezzoPerNotte;
    }

    public void setPrezzoPerNotte(Double prezzoPerNotte) {
        this.prezzoPerNotte = prezzoPerNotte;
    }
}
