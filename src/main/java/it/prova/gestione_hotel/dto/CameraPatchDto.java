package it.prova.gestione_hotel.dto;

public class CameraPatchDto {
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
