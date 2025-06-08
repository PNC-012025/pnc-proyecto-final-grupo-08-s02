package com.pnc.project.service;

import com.pnc.project.dto.request.formulario.FormularioRequest;
import com.pnc.project.dto.response.formulario.FormularioResponse;
import com.pnc.project.dto.response.usuario.UsuarioResponse;

import java.time.LocalDate;
import java.util.List;

public interface FormularioService {
    List<FormularioResponse> findAll();
    FormularioResponse findById(int id);
    FormularioResponse findByDate(LocalDate fechaCreacion);
    FormularioResponse findByEstado(String estado);
    FormularioResponse save(FormularioRequest formulario);
    void delete(int id);
    List<UsuarioResponse> getUsuarioRequests(String codigoUsuario);
}
