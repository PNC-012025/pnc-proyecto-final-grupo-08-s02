package com.pnc.project.service;

import com.pnc.project.dto.request.actividad.ActividadRequest;
import com.pnc.project.dto.response.actividad.ActividadResponse;

import java.util.List;

public interface ActividadService {
    //Listar Actividades
    List<ActividadResponse> findAll();
    //Crear Actividad
    ActividadResponse save(ActividadRequest actividad);
    //Actualizar Actividad
    ActividadResponse update(ActividadRequest actividad);
    //Eliminar actividad
    void delete(int id);

    //Listar por tipo
    List<ActividadResponse> findByTipo(String tipo);
}
