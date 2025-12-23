package it.prova.gestione_hotel.dto;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

public class UtenteDto {
    private Long id;
    private String nome;
    private String cognome;
    private String codiceFiscale;
    private Double creditoDisponibile;
    private Set<PrenotazioneDto> prenotazioni = new HashSet<>();

    public UtenteDto(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    public Double getCreditoDisponibile() {
        return creditoDisponibile;
    }

    public void setCreditoDisponibile(Double creditoDisponibile) {
        this.creditoDisponibile = creditoDisponibile;
    }

    public Set<PrenotazioneDto> getPrenotazioni() {
        return prenotazioni;
    }

    public void setPrenotazioni(Set<PrenotazioneDto> prenotazioni) {
        this.prenotazioni = prenotazioni;
    }

    @Override
    public String toString() {
        return "Utente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", codiceFiscale='" + codiceFiscale + '\'' +
                ", creditoDisponibile=" + creditoDisponibile +
                ", prenotazioni=" + prenotazioni +
                '}';
    }
}
