package com.pnc.project.service;

import com.pnc.project.dto.request.rol.RolRequest;
import com.pnc.project.dto.request.usuario.UsuarioRequest;
import com.pnc.project.dto.response.rol.RolResponse;
import com.pnc.project.dto.response.usuario.UsuarioResponse;


import java.util.List;

public interface UsuarioService {
    //Listar usuarios
    List<UsuarioResponse> findAll();
    //Listar usuario por materia
    List<UsuarioResponse> findByMateria(String materia);

    //Obtener usuario por id
    UsuarioResponse findById(int id);
    //Obtener usuario por código
    UsuarioResponse findByCodigo(String codigo);
    //Obtener usuario por rol
    UsuarioResponse findByRol(String rol);

    //Crear usuario
    UsuarioResponse save(UsuarioRequest usuario);
    //Actualizar usuario
    UsuarioResponse update(UsuarioRequest usuario);
    //Eliminar usuario
    void delete(int id);



    //login
    UsuarioResponse login(String email, String password);

}
