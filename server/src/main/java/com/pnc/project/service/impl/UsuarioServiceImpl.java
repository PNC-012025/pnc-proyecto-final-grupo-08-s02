package com.pnc.project.service.impl;

import com.pnc.project.dto.request.usuario.UsuarioRequest;
import com.pnc.project.dto.response.rol.RolResponse;
import com.pnc.project.dto.response.usuario.UsuarioResponse;
import com.pnc.project.repository.UsuarioRepository;
import com.pnc.project.service.RolService;
import com.pnc.project.service.UsuarioService;
import com.pnc.project.utils.mappers.RolMapper;
import com.pnc.project.utils.mappers.UsuarioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RolService rolService;

    @Autowired
    public UsuarioServiceImpl(UsuarioRepository repository, RolService rolService) {
        this.usuarioRepository = repository;
        this.rolService = rolService;
    }

    @Override
    public List<UsuarioResponse> findAll(){ return UsuarioMapper.toDTOList(usuarioRepository.findAll()); }

    @Override
    public UsuarioResponse findById(int id) {
        return UsuarioMapper.toDTO(usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario not found")));
    }

    @Override
    public UsuarioResponse findByCodigo(String codigo) {
        return UsuarioMapper.toDTO(usuarioRepository.findByCodigoUsuario(codigo)
                .orElseThrow(() -> new RuntimeException("Usuario not found")));
    }

    @Override
    public UsuarioResponse save(UsuarioRequest usuario) {
        RolResponse rol = rolService.findByName(usuario.getRol());
        return UsuarioMapper.toDTO(usuarioRepository.save(UsuarioMapper.toEntityCreate(usuario, RolMapper.toEntity(rol))));
    }

    @Override
    public void delete(int id) {usuarioRepository.deleteById(id);}


}
