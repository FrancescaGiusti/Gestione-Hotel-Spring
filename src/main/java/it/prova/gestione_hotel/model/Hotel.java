package it.prova.gestione_hotel.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "hotel")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "citta")
    private String citta;
    @Column(name = "indirizzo")
    private String indirizzo;
    @Column(name = "civico")
    private Integer civico;
    @Column(name = "nome")
    private String nome;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "hotel", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Set<Camera> camere = new HashSet<>();

    public Hotel(){}

    public Hotel(String citta, String indirizzo, Integer civico, String nome) {
        this.citta = citta;
        this.indirizzo = indirizzo;
        this.civico = civico;
        this.nome = nome;
    }

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

    public Set<Camera> getCamere() {
        return camere;
    }

    public void setCamere(Set<Camera> camere) {
        this.camere = camere;
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
