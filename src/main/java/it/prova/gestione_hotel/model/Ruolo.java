package it.prova.gestione_hotel.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ruolo")
public class Ruolo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "codice")
    private Codice codice;

    @Column(name = "descrizione")
    private String descrizione;

    @ManyToMany(mappedBy = "ruoli")
    private Set<Utente> utenti = new HashSet<>();

    public Ruolo() {}

    public Ruolo(Codice codice, String descrizione) {
        this.codice = codice;
        this.descrizione = descrizione;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Codice getCodice() {
        return codice;
    }

    public void setCodice(Codice codice) {
        this.codice = codice;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public Set<Utente> getUtenti() {
        return utenti;
    }

    public void setUtenti(Set<Utente> utenti) {
        this.utenti = utenti;
    }
}
