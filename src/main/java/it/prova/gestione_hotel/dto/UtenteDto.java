package it.prova.gestione_hotel.dto;

import it.prova.gestione_hotel.model.Prenotazione;
import it.prova.gestione_hotel.model.Utente;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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

    public static UtenteDto fromModel(Utente utente) {
        UtenteDto utenteDto = new UtenteDto();
        utenteDto.setNome(utente.getNome());
        utenteDto.setCognome(utente.getCognome());
        utenteDto.setCodiceFiscale(utente.getCodiceFiscale());
        utenteDto.setId(utente.getId());
        utenteDto.setPrenotazioni(PrenotazioneDto.fromModelLight(utente.getPrenotazioni()));
        utenteDto.setCreditoDisponibile(utente.getCreditoDisponibile());
        return utenteDto;
    }

    public static Set<UtenteDto> fromModel(Set<Utente> utente){
        return utente.stream().map(u -> UtenteDto.fromModel(u)).collect(Collectors.toSet());
    }

    public Utente toModel(){
        Utente utente = new Utente();
        utente.setNome(this.getNome());
        utente.setCognome(this.getCognome());
        utente.setCodiceFiscale(this.getCodiceFiscale());
        utente.setPrenotazioni((this.getPrenotazioni().stream().map(p-> p.toModel()).collect(Collectors.toSet())));
        utente.setId(this.getId());
        utente.setCreditoDisponibile(this.getCreditoDisponibile());
        return utente;
    }

    public static UtenteDto fromModelLight(Utente utente) {
        UtenteDto utenteDto = new UtenteDto();
        utenteDto.setNome(utente.getNome());
        utenteDto.setCognome(utente.getCognome());
        utenteDto.setCodiceFiscale(utente.getCodiceFiscale());
        utenteDto.setId(utente.getId());
        utenteDto.setCreditoDisponibile(utenteDto.getCreditoDisponibile());
        return utenteDto;
    }

    public static Set<UtenteDto> fromModelLight(Set<Utente> utente){
        return utente.stream().map(u -> UtenteDto.fromModelLight(u)).collect(Collectors.toSet());
    }
}
