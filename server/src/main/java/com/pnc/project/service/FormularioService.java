package com.pnc.project.service;

import com.pnc.project.dto.request.formulario.FormularioRequest;
import com.pnc.project.dto.response.formulario.FormularioResponse;
import com.pnc.project.dto.response.usuario.UsuarioResponse;
import com.pnc.project.entities.Usuario;

import java.time.LocalDate;
import java.util.List;

public interface FormularioService {
    //Listar formulario por usuarios
    List<FormularioResponse> findAllUsers(Usuario usuario);
    //Obtener formulario por id
    FormularioResponse findById(int id);

    //Crear formulario
    FormularioResponse save(FormularioRequest formulario);
    //Eliminar formulario
    void delete(int id);
    //Actualizar formulario
    FormularioResponse update(FormularioRequest formulario);

    //-------HACEN FALTA-------
    //Obtener los formulario de la semana actual del usuario
    //obtenerFormularioSemanaUsuario(id_usuario, semanaActual)

    //Verificar si ya tiene formulario esta semana
    //verificarSiYaTieneFormularioEstaSemana(id_usuario)

    // Marcar como validado
    //marcarComoValidado(id_formulario, id_encargado)


}
