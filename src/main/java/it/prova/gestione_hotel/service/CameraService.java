package it.prova.gestione_hotel.service;

import it.prova.gestione_hotel.model.Camera;

import java.util.Set;

public interface CameraService {
    Set<Camera> getAll();
    Camera findById(Long id);
    void addRoom(Camera camera);
    void modifyRoom(Camera camera);
    void deleteRoom(Long id);
}
