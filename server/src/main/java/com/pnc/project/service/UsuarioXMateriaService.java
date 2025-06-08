package com.pnc.project.service;

import com.pnc.project.dto.request.usuarioxmateria.UsuarioXMateriaRequest;
import com.pnc.project.dto.response.materia.MateriaResponse;
import com.pnc.project.dto.response.usuario.UsuarioResponse;
import com.pnc.project.dto.response.usuarioxmateria.UsuarioXMateriaResponse;

import java.util.List;

public interface UsuarioXMateriaService {
    List<UsuarioXMateriaResponse> findAll();
    UsuarioXMateriaResponse obtenerUsuarioXMateriaPorId(int idUsuarioXMateria);
    UsuarioXMateriaResponse save(UsuarioXMateriaRequest usuarioXMateria);
    void delete(int idUsuarioXMateria);
    List<UsuarioResponse> obtenerUsuarioPorMateria(String codigoUsuario);
    List<MateriaResponse> obtenerMateriaPorUsuario(String nombreMateria);
}
