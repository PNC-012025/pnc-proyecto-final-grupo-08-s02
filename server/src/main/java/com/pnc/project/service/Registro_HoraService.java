package com.pnc.project.service;

import com.pnc.project.dto.request.registro_hora.Registro_HoraRequest;
import com.pnc.project.dto.response.actividad.ActividadResponse;
import com.pnc.project.dto.response.formulario.FormularioResponse;
import com.pnc.project.dto.response.registro_hora.Registro_HoraResponse;
import com.pnc.project.dto.response.usuario.UsuarioResponse;

import java.time.LocalDate;
import java.util.List;

public interface Registro_HoraService {
    List<Registro_HoraResponse> findAll();
    Registro_HoraResponse findById(int id);
    Registro_HoraResponse findByDate(LocalDate fechaRegistro);
    Registro_HoraResponse findByAula(String aula);
    Registro_HoraResponse save(Registro_HoraRequest registroHora);
    void delete(int id);
    List<UsuarioResponse> getUsuarioRequests(String codigoUsuario);
    List<ActividadResponse> getActividadRequests(String nombreActividad);
    List<FormularioResponse> getFormularioRequests(int idFormulario);
}
