package it.prova.gestione_hotel.dto;

import it.prova.gestione_hotel.model.Camera;
import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class CameraDto {
    private Long id;
    private Integer numeroCamera;
    private TipoCamera tipoCamera;
    private Integer maxOcppupanti;
    private Double prezzoPerNotte;
    private HotelDto hotel;
    private Set<PrenotazioneDto> prenotazioni = new HashSet<>();

    public CameraDto(){}

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

    public HotelDto getHotel() {
        return hotel;
    }

    public void setHotel(HotelDto hotel) {
        this.hotel = hotel;
    }

    public Set<PrenotazioneDto> getPrenotazioni() {
        return prenotazioni;
    }

    public void setPrenotazioni(Set<PrenotazioneDto> prenotazioni) {
        this.prenotazioni = prenotazioni;
    }

    public static CameraDto fromModel(Camera camera) {
        CameraDto cameraDto = new CameraDto();
        cameraDto.setHotel(camera.getHotel());
        cameraDto.setId(camera.getId());
        cameraDto.setNumeroCamera(camera.getNumeroCamera());
        cameraDto.setTipoCamera(camera.getTipoCamera());
        cameraDto.setPrenotazioni(camera.getPrenotazioni());
        cameraDto.setMaxOcppupanti(camera.getMaxOcppupanti());
        cameraDto.setPrezzoPerNotte(camera.getPrezzoPerNotte());
        return cameraDto;
    }

    public static Set<CameraDto> fromModel(Set<Camera> camere){
        return camere.stream().map(c -> CameraDto.fromModel(c)).collect(Collectors.toSet());
    }


    public Camera toModel(){
        Camera camera = new Camera();
        camera.setHotel(this.getHotel());
        camera.setNumeroCamera(this.getNumeroCamera());
        camera.setTipoCamera(this.getTipoCamera());
        camera.setId(this.getId());
        camera.setPrenotazioni(this.getPrenotazioni());
        camera.setMaxOcppupanti(this.getMaxOcppupanti());
        camera.setPrezzoPerNotte(this.getPrezzoPerNotte());
        return camera;
    }




}
