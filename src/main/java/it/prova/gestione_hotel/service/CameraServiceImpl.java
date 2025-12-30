package it.prova.gestione_hotel.service;

import it.prova.gestione_hotel.dto.CameraDto;
import it.prova.gestione_hotel.dto.CameraPatchDto;
import it.prova.gestione_hotel.exception.EntityNotFoundException;
import it.prova.gestione_hotel.model.Camera;
import it.prova.gestione_hotel.model.TipoCamera;
import it.prova.gestione_hotel.repository.CameraRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class CameraServiceImpl implements CameraService{
    private final CameraRepository cameraRepository;

    @Autowired
    public CameraServiceImpl(CameraRepository cameraRepository) {
        this.cameraRepository = cameraRepository;
    }

    @Override
    public Set<CameraDto> getAll() {
        return CameraDto.fromModel(cameraRepository.findAll().stream().collect(Collectors.toSet()));
    }

    @Override
    public CameraDto findById(Long id) {
        return CameraDto.fromModel(cameraRepository.findById(id).orElse(null));
    }

    @Override
    public void addRoom(CameraDto cameraDto) {
        if(cameraDto == null)
            throw new RuntimeException("Input non valido");
        cameraRepository.save(cameraDto.toModel());
    }

    @Override
    public void modifyRoom(CameraDto cameraDto) throws EntityNotFoundException {
        if (this.findById(cameraDto.getId()) == null)
            throw new EntityNotFoundException("La camera che vuoi modificare non esiste");
        cameraRepository.save(cameraDto.toModel());
    }

    @Override
    public void deleteRoom(Long id) throws EntityNotFoundException {
        Camera cameraDaEliminare = cameraRepository.findById(id).orElse(null);
        if ( cameraDaEliminare== null)
            throw new EntityNotFoundException("La camera che vuoi eliminare non esiste");
        cameraRepository.delete(cameraDaEliminare);
    }

    @Override
    public void modifyPartiallyCamera(Long id, CameraPatchDto cameraPatchDto) throws EntityNotFoundException {
        Camera camera = cameraRepository.findById(id).orElse(null);
        if (camera == null)
            throw new EntityNotFoundException("Entity non trovata");
        if (cameraPatchDto == null) {
            throw new RuntimeException("Input non valido");
        }
        camera.setPrezzoPerNotte(cameraPatchDto.getPrezzoPerNotte());
        cameraRepository.save(camera);
    }

    @Override
    public Set<CameraDto> findByTipoCamera(TipoCamera tipoCamera) {
        if (tipoCamera == null)
            throw new RuntimeException("Input non valido");
        return CameraDto.fromModel(cameraRepository.findByTipoCamera(tipoCamera));
    }

    @Override
    public Set<CameraDto> findAllPageable(Pageable pageable) {
        Page<Camera> camerePaginated = cameraRepository.findAll(pageable);
        return camerePaginated.stream().map(c-> CameraDto.fromModel(c)).collect(Collectors.toSet());
    }
}
