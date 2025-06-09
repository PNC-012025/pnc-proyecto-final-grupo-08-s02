package com.pnc.project.service.impl;

import com.pnc.project.dto.request.usuarioxmateria.UsuarioXMateriaRequest;
import com.pnc.project.dto.response.materia.MateriaResponse;
import com.pnc.project.dto.response.usuario.UsuarioResponse;
import com.pnc.project.dto.response.usuarioxmateria.UsuarioXMateriaResponse;
import com.pnc.project.entities.Materia;
import com.pnc.project.entities.Usuario;
import com.pnc.project.entities.UsuarioXMateria;
import com.pnc.project.repository.UsuarioXMateriaRepository;
import com.pnc.project.service.MateriaService;
import com.pnc.project.service.UsuarioService;
import com.pnc.project.service.UsuarioXMateriaService;
import com.pnc.project.utils.mappers.MateriaMapper;
import com.pnc.project.utils.mappers.UsuarioMapper;
import com.pnc.project.utils.mappers.UsuarioXMateriaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioXMateriaServiceImpl implements UsuarioXMateriaService {
    private final UsuarioXMateriaRepository usuarioXMateriaRepository;
    private final UsuarioService usuarioService;
    private final MateriaService materiaService;

    @Autowired
    public UsuarioXMateriaServiceImpl(UsuarioXMateriaRepository usuarioXMateriaRepository, UsuarioService usuarioService, MateriaService materiaService) {
        this.usuarioXMateriaRepository = usuarioXMateriaRepository;
        this.usuarioService = usuarioService;
        this.materiaService = materiaService;
    }

    @Override
    public List<UsuarioXMateriaResponse> findAll() {
        return UsuarioXMateriaMapper.toDTOList(usuarioXMateriaRepository.findAll());
    }

    @Override
    public UsuarioXMateriaResponse findById(int id) {
        return UsuarioXMateriaMapper.toDTO(usuarioXMateriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("UsuarioXMateria not found")));
    }

    @Override
    public UsuarioXMateriaResponse save(UsuarioXMateriaRequest usuarioXMateria) {
        UsuarioResponse usuario = usuarioService.findByCodigo(usuarioXMateria.getCodigoUsuario());
        MateriaResponse materia = materiaService.findByName(usuarioXMateria.getNombreMateria());
        // Convertir los DTOs a entidades usando los mappers
        Usuario usuarioEntidad = UsuarioMapper.toEntity(usuario);
        Materia materiaEntidad = MateriaMapper.toEntity(materia);

        // Crear entidad UsuarioXMateria con los objetos ya convertidos
        UsuarioXMateria usuario_materia = UsuarioXMateriaMapper.toEntityCreate(usuarioEntidad, materiaEntidad);

        // Guardar y devolver la respuesta convertida a DTO
        return UsuarioXMateriaMapper.toDTO(usuarioXMateriaRepository.save(usuario_materia));
    }

    @Override
    public void delete(int id) {
        usuarioXMateriaRepository.deleteById(id);
    }

    @Override
    public List<UsuarioResponse> getUsuarioRequest(String codigoUsuario) {
        UsuarioResponse usuario = usuarioService.findByCodigo(codigoUsuario);
        return List.of(usuario); // Se retorna como lista con un solo elemento
    }

    @Override
    public List<MateriaResponse> getMateriaRequest(String nombreMateria) {
        MateriaResponse materia = materiaService.findByName(nombreMateria);
        return List.of(materia); // También como lista
    }


}
