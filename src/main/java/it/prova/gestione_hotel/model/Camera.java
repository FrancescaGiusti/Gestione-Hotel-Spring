package it.prova.gestione_hotel.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "camera")
public class Camera {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "numerocamere")
    private Integer numeroCamera;
    @Enumerated(EnumType.STRING)
    @Column(name = "tipocamera")
    private TipoCamera tipoCamera;
    @Column(name = "maxoccupanti")
    private Integer maxOcppupanti;
    @Column(name = "prezzopernotte")
    private Double prezzoPerNotte;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "camera", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Set<Prenotazione> prenotazioni = new HashSet<>();

    public Camera(){}

    public Camera(Integer numeroCamera, TipoCamera tipocamera, Integer maxOcppupanti, Double prezzoPerNotte, Hotel hotel, Set<Prenotazione> prenotazioni) {
        this.numeroCamera = numeroCamera;
        this.tipoCamera = tipocamera;
        this.maxOcppupanti = maxOcppupanti;
        this.prezzoPerNotte = prezzoPerNotte;
        this.hotel = hotel;
        this.prenotazioni = prenotazioni;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumeroCamera() {
        return numeroCamera;
    }

    public void setNumeroCamera(Integer numeroCamera) {
        this.numeroCamera = numeroCamera;
    }

    public TipoCamera getTipoCamera() {
        return tipoCamera;
    }

    public void setTipoCamera(TipoCamera tipoCamera) {
        this.tipoCamera = tipoCamera;
    }

    public Integer getMaxOcppupanti() {
        return maxOcppupanti;
    }

    public void setMaxOcppupanti(Integer maxOcppupanti) {
        this.maxOcppupanti = maxOcppupanti;
    }

    public Double getPrezzoPerNotte() {
        return prezzoPerNotte;
    }

    public void setPrezzoPerNotte(Double prezzoPerNotte) {
        this.prezzoPerNotte = prezzoPerNotte;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Set<Prenotazione> getPrenotazioni() {
        return prenotazioni;
    }

    public void setPrenotazioni(Set<Prenotazione> prenotazioni) {
        this.prenotazioni = prenotazioni;
    }

    @Override
    public String toString() {
        return "Camera{" +
                "id=" + id +
                ", numeroCamera=" + numeroCamera +
                ", tipo camera=" + tipoCamera +
                ", maxOcppupanti=" + maxOcppupanti +
                ", prezzoPerNotte=" + prezzoPerNotte +
                ", hotel=" + hotel +
                ", prenotazioni=" + prenotazioni +
                '}';
    }
}
