package it.prova.gestione_hotel.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "prenotazione")
public class Prenotazione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "utente_id")
    @Column(name = "utente")
    private Utente utente;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "camera_id")
    @Column(name = "camera")
    private Camera camera;
    @Column(name = "datadiprenotazione")
    private LocalDate dataDiPrenotazione;
    @Column(name = "datainiziosoggiorno")
    private LocalDate dataInizioSoggiorno;
    @Column(name = "dataifinesoggiorno")
    private LocalDate dataFineSoggiorno;
    @Column(name = "annullata")
    private boolean annullata = false;

    public Prenotazione(){}

    public Prenotazione(Utente utente, Camera camera, LocalDate dataDiPrenotazione, LocalDate dataInizioSoggiorno, LocalDate dataFineSoggiorno) {
        this.utente = utente;
        this.camera = camera;
        this.dataDiPrenotazione = dataDiPrenotazione;
        this.dataInizioSoggiorno = dataInizioSoggiorno;
        this.dataFineSoggiorno = dataFineSoggiorno;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
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
