package it.prova.gestione_hotel.service;

import it.prova.gestione_hotel.dto.CameraDto;
import it.prova.gestione_hotel.dto.CameraDtoFiltro;
import it.prova.gestione_hotel.dto.CameraPatchDto;
import it.prova.gestione_hotel.exception.CameraNonTrovataException;
import it.prova.gestione_hotel.exception.InputNonValidoException;
import it.prova.gestione_hotel.model.Camera;
import it.prova.gestione_hotel.model.TipoCamera;
import it.prova.gestione_hotel.repository.CameraRepository;
import it.prova.gestione_hotel.specification.CameraSpecification;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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
        if(cameraRepository.findById(id).orElse(null) == null){
            return null;
        } else {
            return CameraDto.fromModel(cameraRepository.findById(id).orElse(null));
        }
    }

    @Override
    public void addRoom(CameraDto cameraDto) {
        if(cameraDto == null)
            throw new CameraNonTrovataException("Input non valido");
        cameraRepository.save(cameraDto.toModel());
    }

    @Override
    public void modifyRoom(CameraDto cameraDto) throws CameraNonTrovataException {
        if (findById(cameraDto.getId()) == null)
            throw new CameraNonTrovataException("La camera che vuoi modificare non esiste");
        cameraRepository.save(cameraDto.toModel());
    }

    @Override
    public void deleteRoom(Long id) throws CameraNonTrovataException {
        Camera cameraDaEliminare = cameraRepository.findById(id).orElse(null);
        if ( cameraDaEliminare== null)
            throw new CameraNonTrovataException("La camera che vuoi eliminare non esiste");
        cameraRepository.delete(cameraDaEliminare);
    }

    @Override
    public void modifyPartiallyCamera(Long id, CameraPatchDto cameraPatchDto) throws CameraNonTrovataException {
        Camera camera = cameraRepository.findById(id).orElse(null);
        if (camera == null)
            throw new CameraNonTrovataException("Entity non trovata");
        if (cameraPatchDto == null) {
            throw new InputNonValidoException("Input non valido");
        }
        camera.setPrezzoPerNotte(cameraPatchDto.getPrezzoPerNotte());
        cameraRepository.save(camera);
    }

    @Override
    public Set<CameraDto> findByTipoCamera(TipoCamera tipoCamera) {
        if (tipoCamera == null)
            throw new InputNonValidoException("Input non valido");
        return CameraDto.fromModel(cameraRepository.findByTipoCamera(tipoCamera));
    }

    @Override
    public Set<CameraDto> findAllPageable(Pageable pageable) {
        Page<Camera> camerePaginated = cameraRepository.findAll(pageable);
        return camerePaginated.stream().map(c-> CameraDto.fromModel(c)).collect(Collectors.toSet());
    }

    @Override
    public Set<CameraDto> findCameraWithFilter(CameraDtoFiltro filter, Pageable pageable) {
        Specification<Camera> spec = CameraSpecification.withPrenotazioniBetweenDates(filter);

        Page<Camera> camerePaginate = cameraRepository.findAll(spec, pageable);

        return camerePaginate.stream().map(c -> CameraDto.fromModel(c)).collect(Collectors.toSet());
    }

    @Override
    public Set<CameraDto> getCameraByHotel(Long hotelId) {
        return cameraRepository.findByHotelId(hotelId)
                .stream()
                .map(c -> CameraDto.fromModel(c))
                .collect(Collectors.toSet());
    }


}
