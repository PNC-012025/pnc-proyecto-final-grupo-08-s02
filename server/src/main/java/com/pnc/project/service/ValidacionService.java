package com.pnc.project.service;

import com.pnc.project.dto.request.validacion.ValidacionRequest;
import com.pnc.project.dto.response.formulario.FormularioResponse;
import com.pnc.project.dto.response.usuario.UsuarioResponse;
import com.pnc.project.dto.response.validacion.ValidacionResponse;

import java.time.LocalDate;
import java.util.List;

public interface ValidacionService {
    List<ValidacionResponse> findAll();
    ValidacionResponse findById(int id);
    ValidacionResponse findByDate(LocalDate fechaValidacion);
    ValidacionResponse findByEstado(Boolean estadoValidacion);
    ValidacionResponse save(ValidacionRequest validacion);
    void delete(int id);
    List<UsuarioResponse> getUsuarioRequests(String codigoUsuario);
    List<FormularioResponse> getFormularioRequests(int idFormulario);
}
