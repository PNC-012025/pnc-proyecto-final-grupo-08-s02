package com.pnc.project.service.impl;

import com.pnc.project.dto.response.rol.RolResponse;
import com.pnc.project.repository.RolRepository;
import com.pnc.project.service.RolService;
import com.pnc.project.utils.mappers.RolMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolServiceImpl implements RolService {
    private final RolRepository rolRepository;

    @Autowired
    public RolServiceImpl(RolRepository repository) {this.rolRepository = repository;}

    @Override
    public List<RolResponse> findAll(){return RolMapper.toDTOList(rolRepository.findAll());}

    @Override
    public RolResponse findByName(String name) {
        return RolMapper.toDTO(rolRepository.findByNombreRol(name)
                .orElseThrow(() -> new RuntimeException("Rol not found")));
    }

}
