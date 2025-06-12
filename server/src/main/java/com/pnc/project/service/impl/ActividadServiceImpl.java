package com.pnc.project.service.impl;

import com.pnc.project.dto.request.actividad.ActividadRequest;
import com.pnc.project.dto.response.actividad.ActividadResponse;
import com.pnc.project.repository.ActividadRepository;
import com.pnc.project.service.ActividadService;
import com.pnc.project.utils.mappers.ActividadMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActividadServiceImpl implements ActividadService {

    private final ActividadRepository actividadRepository;

    public ActividadServiceImpl(ActividadRepository actividadRepository) {
        this.actividadRepository = actividadRepository;
    }

    @Override
    public List<ActividadResponse> findAll() {
        return ActividadMapper.toDTOList(actividadRepository.findAll());
    }

    @Override
    public ActividadResponse findById(int id) {
        return ActividadMapper.toDTO(actividadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Actividad not found")));
    }

    @Override
    public ActividadResponse save(ActividadRequest actividad) {
        return ActividadMapper.toDTO(actividadRepository.save(ActividadMapper.toEntityCreate(actividad)));
    }

    @Override
    public ActividadResponse update(ActividadRequest actividad) {
        return ActividadMapper.toDTO(actividadRepository.save(ActividadMapper.toEntityUpdate(actividad)));
    }

    @Override
    public void delete(int id) {
        actividadRepository.deleteById(id);
    }

    @Override
    public List<ActividadResponse> findByTipo(String tipo) {
        return ActividadMapper.toDTOList(actividadRepository.findByTipoActividad(tipo));
    }

}
