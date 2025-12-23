package it.prova.gestione_hotel.dto;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

public class HotelDto {
    private Long id;
    private String citta;
    private String indirizzo;
    private Integer civico;
    private String nome;
    private Set<CameraDto> camere = new HashSet<>();

    public HotelDto(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public Integer getCivico() {
        return civico;
    }

    public void setCivico(Integer civico) {
        this.civico = civico;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", citta='" + citta + '\'' +
                ", indirizzo='" + indirizzo + '\'' +
                ", civico=" + civico +
                ", nome='" + nome + '\'' +
                '}';
    }
}
