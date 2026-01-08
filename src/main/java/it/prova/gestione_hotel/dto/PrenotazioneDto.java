package it.prova.gestione_hotel.dto;

import it.prova.gestione_hotel.model.Hotel;
import it.prova.gestione_hotel.model.Prenotazione;
import it.prova.gestione_hotel.validation.annotation.Today;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

public class PrenotazioneDto {
    private Long id;
    @NotNull
    private UtenteDto utente;
    private CameraDto camera;
    @NotNull
    @Today
    private LocalDate dataDiPrenotazione;
    @Future
    @NotNull
    private LocalDate dataInizioSoggiorno;
    @Future
    @NotNull
    private LocalDate dataFineSoggiorno;
    private boolean annullata = false;

    public PrenotazioneDto(){}


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UtenteDto getUtente() {
        return utente;
    }

    public void setUtente(UtenteDto utente) {
        this.utente = utente;
    }

    public CameraDto getCamera() {
        return camera;
    }

    public void setCamera(CameraDto camera) {
        this.camera = camera;
    }

    public LocalDate getDataDiPrenotazione() {
        return dataDiPrenotazione;
    }

    public void setDataDiPrenotazione(LocalDate dataDiPrenotazione) {
        this.dataDiPrenotazione = dataDiPrenotazione;
    }

    public LocalDate getDataInizioSoggiorno() {
        return dataInizioSoggiorno;
    }

    public void setDataInizioSoggiorno(LocalDate dataInizioSoggiorno) {
        this.dataInizioSoggiorno = dataInizioSoggiorno;
    }

    public LocalDate getDataFineSoggiorno() {
        return dataFineSoggiorno;
    }

    public void setDataFineSoggiorno(LocalDate dataFineSoggiorno) {
        this.dataFineSoggiorno = dataFineSoggiorno;
    }

    public boolean isAnnullata() {
        return annullata;
    }

    public void setAnnullata(boolean annullata) {
        this.annullata = annullata;
    }

    public static PrenotazioneDto fromModel(Prenotazione prenotazione) {
        PrenotazioneDto prenotazioneDto = new PrenotazioneDto();
        prenotazioneDto.setAnnullata(prenotazione.isAnnullata());
        prenotazioneDto.setDataDiPrenotazione(prenotazione.getDataDiPrenotazione());
        prenotazioneDto.setCamera(CameraDto.fromModelLight(prenotazione.getCamera()));
        prenotazioneDto.setId(prenotazione.getId());
        prenotazioneDto.setUtente(UtenteDto.fromModelLight(prenotazione.getUtente()));
        prenotazioneDto.setDataInizioSoggiorno(prenotazione.getDataInizioSoggiorno());
        prenotazioneDto.setDataFineSoggiorno(prenotazione.getDataFineSoggiorno());
        return prenotazioneDto;
    }

    public static Set<PrenotazioneDto> fromModel(Set<Prenotazione> prenotazione){
        return prenotazione.stream().map(p -> PrenotazioneDto.fromModel(p)).collect(Collectors.toSet());
    }

    public Prenotazione toModel(){
        Prenotazione prenotazione = new Prenotazione();
        prenotazione.setAnnullata(this.isAnnullata());
        prenotazione.setCamera(this.getCamera().toModel());
        prenotazione.setDataDiPrenotazione(this.getDataDiPrenotazione());
        prenotazione.setUtente(this.getUtente().toModel());
        prenotazione.setDataInizioSoggiorno(this.getDataInizioSoggiorno());
        prenotazione.setDataFineSoggiorno(this.getDataFineSoggiorno());
        prenotazione.setId(this.getId());
        return prenotazione;
    }

    public static PrenotazioneDto fromModelLight(Prenotazione prenotazione) {
        PrenotazioneDto prenotazioneDto = new PrenotazioneDto();
        prenotazioneDto.setAnnullata(prenotazioneDto.isAnnullata());
        prenotazioneDto.setDataDiPrenotazione(prenotazione.getDataDiPrenotazione());
        prenotazioneDto.setId(prenotazioneDto.getId());
        prenotazioneDto.setDataInizioSoggiorno(prenotazione.getDataInizioSoggiorno());
        prenotazioneDto.setDataFineSoggiorno(prenotazione.getDataFineSoggiorno());
        return prenotazioneDto;
    }

    public static Set<PrenotazioneDto> fromModelLight(Set<Prenotazione> prenotazione){
        return prenotazione.stream().map(p -> PrenotazioneDto.fromModelLight(p)).collect(Collectors.toSet());
    }
}
