package it.prova.gestione_hotel.service;

import it.prova.gestione_hotel.dto.CameraDto;
import it.prova.gestione_hotel.dto.CameraPatchDto;
import it.prova.gestione_hotel.model.Camera;

import java.util.Set;

public interface CameraService {
    Set<CameraDto> getAll();
    CameraDto findById(Long id);
    void addRoom(CameraDto cameraDto);
    void modifyRoom(CameraDto cameraDto);
    void deleteRoom(Long id);
    void modifyPartiallyCamera(Long id, CameraPatchDto cameraPatchDto);
}
