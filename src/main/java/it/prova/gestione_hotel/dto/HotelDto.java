package it.prova.gestione_hotel.dto;

import it.prova.gestione_hotel.model.Hotel;
import jakarta.validation.constraints.NotBlank;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class HotelDto {
    private Long id;
    @NotBlank
    private String citta;
    @NotBlank
    private String indirizzo;
    private Integer civico;
    @NotBlank
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

    public Set<CameraDto> getCamere() {
        return camere;
    }

    public void setCamere(Set<CameraDto> camere) {
        this.camere = camere;
    }

    public static HotelDto fromModel(Hotel hotel) {
        HotelDto hotelDto = new HotelDto();
        hotelDto.setCitta(hotel.getCitta());
        hotelDto.setId(hotel.getId());
        hotelDto.setCivico(hotel.getCivico());
        hotelDto.setNome(hotel.getNome());
        hotelDto.setIndirizzo(hotel.getIndirizzo());
        hotelDto.setCamere(CameraDto.fromModelLight(hotel.getCamere()));
        return hotelDto;
    }

    public static Set<HotelDto> fromModel(Set<Hotel> hotel){
        return hotel.stream().map(h -> HotelDto.fromModel(h)).collect(Collectors.toSet());
    }

    public Hotel toModel(){
        Hotel hotel = new Hotel();
        hotel.setCitta(this.getCitta());
        hotel.setCivico(this.getCivico());
        hotel.setNome(this.getNome());
        hotel.setIndirizzo(this.getIndirizzo());
        hotel.setId(this.getId());
        return hotel;
    }

    public static HotelDto fromModelLight(Hotel hotel) {
        HotelDto hotelDto = new HotelDto();
        hotelDto.setCitta(hotel.getCitta());
        hotelDto.setId(hotel.getId());
        hotelDto.setCivico(hotel.getCivico());
        hotelDto.setNome(hotel.getNome());
        hotelDto.setIndirizzo(hotel.getIndirizzo());
        return hotelDto;
    }

    public static Set<HotelDto> fromModellight(Set<Hotel> hotel){
        return hotel.stream().map(h -> HotelDto.fromModelLight(h)).collect(Collectors.toSet());
    }
}
