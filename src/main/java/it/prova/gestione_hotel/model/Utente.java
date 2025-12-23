package it.prova.gestione_hotel.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "utente")
public class Utente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "cognome")
    private String cognome;
    @Column(name = "codicefiscale")
    private String codiceFiscale;
    @Column(name = "creditodisponibile")
    private Double creditoDisponibile;
    @OneToMany(mappedBy = "utente", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @Column(name = "prenotazioni")
    private Set<Prenotazione> prenotazioni = new HashSet<>();

    public Utente(){}

    public Utente(String nome, String cognome, String codiceFiscale, Double creditoDisponibile, Set<Prenotazione> prenotazioni) {
        this.nome = nome;
        this.cognome = cognome;
        this.codiceFiscale = codiceFiscale;
        this.creditoDisponibile = creditoDisponibile;
        this.prenotazioni = prenotazioni;
    }

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

    public Set<Prenotazione> getPrenotazioni() {
        return prenotazioni;
    }

    public void setPrenotazioni(Set<Prenotazione> prenotazioni) {
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
