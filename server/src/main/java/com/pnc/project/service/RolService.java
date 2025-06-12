package com.pnc.project.service;

import com.pnc.project.dto.request.rol.RolRequest;
import com.pnc.project.dto.response.rol.RolResponse;

import java.util.List;

public interface RolService {
    //Listar roles
    List<RolResponse> findAll();
    //Únicamente este metodo puesto que son roles fijos de un ENUM

}
