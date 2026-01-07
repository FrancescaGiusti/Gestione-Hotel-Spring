package it.prova.gestione_hotel.service;

import it.prova.gestione_hotel.dto.CameraDto;
import it.prova.gestione_hotel.dto.CameraDtoFiltro;
import it.prova.gestione_hotel.dto.CameraPatchDto;
import it.prova.gestione_hotel.exception.EntityNotFoundException;
import it.prova.gestione_hotel.model.TipoCamera;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface CameraService {
    Set<CameraDto> getAll();
    CameraDto findById(Long id);
    void addRoom(CameraDto cameraDto);
    void modifyRoom(CameraDto cameraDto) throws EntityNotFoundException;
    void deleteRoom(Long id) throws EntityNotFoundException;
    void modifyPartiallyCamera(Long id, CameraPatchDto cameraPatchDto) throws EntityNotFoundException;
    Set<CameraDto> findByTipoCamera(TipoCamera tipoCamera);
    Set<CameraDto> findAllPageable(Pageable pageable);
    Set<CameraDto> findCameraWithFilter (CameraDtoFiltro filter, Pageable pageable);
}
