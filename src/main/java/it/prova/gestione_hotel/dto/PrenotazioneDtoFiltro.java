package it.prova.gestione_hotel.dto;

import java.time.LocalDate;

public class PrenotazioneDtoFiltro {
    private Long idUtente;
    private Long idCamera;
    private LocalDate dataDa;
    private LocalDate dataA;
    private Boolean annullata;

    public Long getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(Long idUtente) {
        this.idUtente = idUtente;
    }

    public Long getIdCamera() {
        return idCamera;
    }

    public void setIdCamera(Long idCamera) {
        this.idCamera = idCamera;
    }

    public LocalDate getDataDa() {
        return dataDa;
    }

    public void setDataDa(LocalDate dataDa) {
        this.dataDa = dataDa;
    }

    public LocalDate getDataA() {
        return dataA;
    }

    public void setDataA(LocalDate dataA) {
        this.dataA = dataA;
    }

    public Boolean getAnnullata() {
        return annullata;
    }

    public void setAnnullata(Boolean annullata) {
        this.annullata = annullata;
    }
}
