package it.prova.gestione_hotel.dto;

import it.prova.gestione_hotel.model.Camera;
import it.prova.gestione_hotel.model.TipoCamera;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class CameraDto {
    private Long id;
    @NotNull
    private Integer numeroCamera;
    @NotNull
    private TipoCamera tipoCamera;
    @NotNull
    @Positive
    private Integer maxOcppupanti;
    @NotNull
    @Positive
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
        cameraDto.setHotel(HotelDto.fromModelLight(camera.getHotel()));
        cameraDto.setId(camera.getId());
        cameraDto.setNumeroCamera(camera.getNumeroCamera());
        cameraDto.setTipoCamera(camera.getTipoCamera());
        cameraDto.setPrenotazioni(PrenotazioneDto.fromModelLight(camera.getPrenotazioni()));
        cameraDto.setMaxOcppupanti(camera.getMaxOcppupanti());
        cameraDto.setPrezzoPerNotte(camera.getPrezzoPerNotte());
        return cameraDto;
    }

    public static Set<CameraDto> fromModel(Set<Camera> camere){
        return camere.stream().map(c -> CameraDto.fromModel(c)).collect(Collectors.toSet());
    }


    public Camera toModel(){
        Camera camera = new Camera();
        camera.setHotel(this.getHotel() != null ? this.getHotel().toModel() : null);
        camera.setNumeroCamera(this.getNumeroCamera());
        camera.setTipoCamera(this.getTipoCamera());
        camera.setId(this.getId());
        camera.setPrenotazioni(this.getPrenotazioni().stream().map(p-> p.toModel()).collect(Collectors.toSet()));
        camera.setMaxOcppupanti(this.getMaxOcppupanti());
        camera.setPrezzoPerNotte(this.getPrezzoPerNotte());
        return camera;
    }

    public static CameraDto fromModelLight(Camera camera) {
        CameraDto cameraDto = new CameraDto();
        cameraDto.setId(camera.getId());
        cameraDto.setNumeroCamera(camera.getNumeroCamera());
        cameraDto.setTipoCamera(camera.getTipoCamera());
        cameraDto.setMaxOcppupanti(camera.getMaxOcppupanti());
        cameraDto.setPrezzoPerNotte(camera.getPrezzoPerNotte());
        return cameraDto;
    }

    public static Set<CameraDto> fromModelLight(Set<Camera> camere){
        return camere.stream().map(c -> CameraDto.fromModelLight(c)).collect(Collectors.toSet());
    }



}
