package it.prova.gestione_hotel.dto;

import it.prova.gestione_hotel.model.Prenotazione;
import it.prova.gestione_hotel.model.Ruolo;
import it.prova.gestione_hotel.model.Utente;
import it.prova.gestione_hotel.validation.annotation.CodiceFiscale;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class UtenteDto {
    private Long id;
    @NotBlank
    @Size(min = 3, message = "Lo username deve contenere almeno 3 caratteri")
    private String username;
    @NotBlank
    @Size(min = 5, message = "La password deve contenere almeno 5 caratteri")
    private String password;
    @NotBlank
    @Size(min = 3, message = "Il cognome deve contenere almeno 3 caratteri")
    private String nome;
    @NotBlank
    @Size(min = 3, message = "Il cognome deve contenere almeno 3 caratteri")
    private String cognome;
    @NotBlank
    @CodiceFiscale
    private String codiceFiscale;
    private Double creditoDisponibile;
    private Set<PrenotazioneDto> prenotazioni = new HashSet<>();
    private Set<RuoloDto> ruoli = new HashSet<>();

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<RuoloDto> getRuoli() {
        return ruoli;
    }

    public void setRuoli(Set<RuoloDto> ruoli) {
        this.ruoli = ruoli;
    }

    public static UtenteDto fromModel(Utente utente) {
        UtenteDto utenteDto = new UtenteDto();
        utenteDto.setNome(utente.getNome());
        utenteDto.setCognome(utente.getCognome());
        utenteDto.setCodiceFiscale(utente.getCodiceFiscale());
        utenteDto.setId(utente.getId());
        utenteDto.setPrenotazioni(PrenotazioneDto.fromModelLight(utente.getPrenotazioni()));
        utenteDto.setCreditoDisponibile(utente.getCreditoDisponibile());
        utenteDto.setUsername(utente.getUsername());
        utenteDto.setRuoli(RuoloDto.fromModelLight(utente.getRuoli()));
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
        utente.setCreditoDisponibile(this.getCreditoDisponibile() == null ? 0 : this.getCreditoDisponibile());
        utente.setUsername(this.getUsername());
        utente.setPassword(this.getPassword());
        utente.setRuoli(this.getRuoli().stream().map(r -> r.toModel()).collect(Collectors.toSet()));
        return utente;
    }

    public static UtenteDto fromModelLight(Utente utente) {
        UtenteDto utenteDto = new UtenteDto();
        utenteDto.setNome(utente.getNome());
        utenteDto.setCognome(utente.getCognome());
        utenteDto.setCodiceFiscale(utente.getCodiceFiscale());
        utenteDto.setId(utente.getId());
        utenteDto.setCreditoDisponibile(utente.getCreditoDisponibile());
        utenteDto.setUsername(utente.getUsername());
        return utenteDto;
    }

    public static Set<UtenteDto> fromModelLight(Set<Utente> utente){
        return utente.stream().map(u -> UtenteDto.fromModelLight(u)).collect(Collectors.toSet());
    }
}
