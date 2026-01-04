package it.prova.gestione_hotel.dto;

import it.prova.gestione_hotel.model.Utente;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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
