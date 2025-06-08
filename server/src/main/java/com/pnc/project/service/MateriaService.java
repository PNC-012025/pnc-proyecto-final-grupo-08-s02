package com.pnc.project.service;

import com.pnc.project.dto.request.materia.MateriaRequest;
import com.pnc.project.dto.response.materia.MateriaResponse;

import java.util.List;

public interface MateriaService {
    List<MateriaResponse> findAll();
    MateriaResponse findById(int id);
    MateriaResponse findByName(String nombre);
    MateriaResponse save(MateriaRequest materia);
    //Por el momento no se implementa la actualización de materias ni el delete
}
