package it.prova.gestione_hotel.dto;

import jakarta.persistence.*;

import java.time.LocalDate;

public class PrenotazioneDto {
    private Long id;
    private UtenteDto utente;
    private CameraDto camera;
    private LocalDate dataDiPrenotazione;
    private LocalDate dataInizioSoggiorno;
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

    @Override
    public String toString() {
        return "Prenotazione{" +
                "id=" + id +
                ", utente=" + utente +
                ", camera=" + camera +
                ", dataDiPrenotazione=" + dataDiPrenotazione +
                ", dataInizioSoggiorno=" + dataInizioSoggiorno +
                ", dataFineSoggiorno=" + dataFineSoggiorno +
                ", annullata=" + annullata +
                '}';
    }
}
