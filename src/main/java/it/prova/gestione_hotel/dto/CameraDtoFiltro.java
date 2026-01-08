package it.prova.gestione_hotel.dto;

import jakarta.validation.constraints.Future;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class CameraDtoFiltro {
    @Future
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dataInizioDa;
    @Future
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dataInizioA;
    @Future
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dataFineDa;
    @Future
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dataFineA;

    public LocalDate getDataInizioDa() {
        return dataInizioDa;
    }

    public void setDataInizioDa(LocalDate dataInizioDa) {
        this.dataInizioDa = dataInizioDa;
    }

    public LocalDate getDataInizioA() {
        return dataInizioA;
    }

    public void setDataInizioA(LocalDate dataInizioA) {
        this.dataInizioA = dataInizioA;
    }

    public LocalDate getDataFineDa() {
        return dataFineDa;
    }

    public void setDataFineDa(LocalDate dataFineDa) {
        this.dataFineDa = dataFineDa;
    }

    public LocalDate getDataFineA() {
        return dataFineA;
    }

    public void setDataFineA(LocalDate dataFineA) {
        this.dataFineA = dataFineA;
    }
}
