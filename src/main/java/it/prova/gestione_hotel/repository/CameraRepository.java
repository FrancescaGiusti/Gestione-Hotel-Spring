package it.prova.gestione_hotel.repository;

import it.prova.gestione_hotel.model.Camera;
import it.prova.gestione_hotel.model.TipoCamera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CameraRepository extends JpaRepository<Camera, Long> {
    Set<Camera> findByTipoCamera(TipoCamera tipoCamera);
}
