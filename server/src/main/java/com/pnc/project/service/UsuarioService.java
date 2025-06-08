package com.pnc.project.service;

import com.pnc.project.dto.request.usuario.UsuarioRequest;
import com.pnc.project.dto.response.rol.RolResponse;
import com.pnc.project.dto.response.usuario.UsuarioResponse;


import java.util.List;

public interface UsuarioService {
    List<UsuarioResponse> findAll();
    UsuarioResponse findById(int id);
    UsuarioResponse findByCodigo(String codigo);
    UsuarioResponse save(UsuarioRequest usuario);
    void delete(int id);
    List<RolResponse> getRolRequests(int idUsuario);

}
