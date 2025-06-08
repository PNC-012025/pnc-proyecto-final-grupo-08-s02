package com.pnc.project.service;

import com.pnc.project.dto.request.rol.RolRequest;
import com.pnc.project.dto.response.rol.RolResponse;

import java.util.List;

public interface RolService {
    List<RolResponse> findAll();
    RolResponse findById(int id);
    RolResponse findByName(String nombre);
    RolResponse save(RolRequest rol);
    //No tendremos Update ni Delete de roles, ya que son fijos y no se modifican

}
