package com.pnc.project.service;

import com.pnc.project.dto.request.actividad.ActividadRequest;
import com.pnc.project.dto.response.actividad.ActividadResponse;

import java.util.List;

public interface ActividadService {
    List<ActividadResponse> findAll();
    ActividadResponse findByName(String nombreActividad);
    ActividadResponse findByTipoActividad(String tipoActividad);
    ActividadResponse save(ActividadRequest actividad);
}
