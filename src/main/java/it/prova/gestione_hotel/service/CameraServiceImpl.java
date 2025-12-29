package it.prova.gestione_hotel.service;

import it.prova.gestione_hotel.dto.CameraDto;
import it.prova.gestione_hotel.dto.CameraPatchDto;
import it.prova.gestione_hotel.model.Camera;
import it.prova.gestione_hotel.repository.CameraRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void modifyRoom(CameraDto cameraDto) {
        if (this.findById(cameraDto.getId()) == null)
            throw new RuntimeException("La camera che vuoi modificare non esiste");
        cameraRepository.save(cameraDto.toModel());
    }

    @Override
    public void deleteRoom(Long id) {
        Camera cameraDaEliminare = cameraRepository.findById(id).orElse(null);
        if ( cameraDaEliminare== null)
            throw new RuntimeException("La camera che vuoi eliminare non esiste");
        cameraRepository.delete(cameraDaEliminare);
    }

    @Override
    public void modifyPartiallyCamera(Long id, CameraPatchDto cameraPatchDto) {
        Camera camera = cameraRepository.findById(id).orElse(null);
        if (cameraPatchDto == null || camera == null) {
            throw new RuntimeException("Input non valido");
        }
        camera.setPrezzoPerNotte(cameraPatchDto.getPrezzoPerNotte());
        cameraRepository.save(camera);
    }
}
