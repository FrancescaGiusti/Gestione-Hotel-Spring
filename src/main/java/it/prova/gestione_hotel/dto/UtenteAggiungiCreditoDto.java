package it.prova.gestione_hotel.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class UtenteAggiungiCreditoDto {
    @NotNull
    private Long id;
    @NotNull
    @Positive
    private Double creditoDaAggiungere;

    public UtenteAggiungiCreditoDto(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getCreditoDaAggiungere() {
        return creditoDaAggiungere;
    }

    public void setCreditoDaAggiungere(Double creditoDaAggiungere) {
        this.creditoDaAggiungere = creditoDaAggiungere;
    }
}
