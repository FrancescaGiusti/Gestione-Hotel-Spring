package it.prova.gestione_hotel.service;

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
    public Set<Camera> getAll() {
        return cameraRepository.findAll().stream().collect(Collectors.toSet());
    }

    @Override
    public Camera findById(Long id) {
        return cameraRepository.findById(id).orElse(null);
    }

    @Override
    public void addRoom(Camera camera) {
        if(camera == null)
            throw new RuntimeException("Input non valido");
        cameraRepository.save(camera);
    }

    @Override
    public void modifyRoom(Camera camera) {
        if (cameraRepository.findById(camera.getId()) == null)
            throw new RuntimeException("La camera che vuoi modificare non esiste");
        cameraRepository.save(camera);
    }

    @Override
    public void deleteRoom(Long id) {
        Camera cameraDaEliminare = cameraRepository.findById(id).orElse(null);
        if ( cameraDaEliminare== null)
            throw new RuntimeException("La camera che vuoi eliminare non esiste");
        cameraRepository.delete(cameraDaEliminare);
    }
}
