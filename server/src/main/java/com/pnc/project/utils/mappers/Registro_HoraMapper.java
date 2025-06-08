package com.pnc.project.utils.mappers;

import com.pnc.project.dto.request.registro_hora.Registro_HoraRequest;
import com.pnc.project.dto.response.registro_hora.Registro_HoraResponse;
import com.pnc.project.entities.Actividad;
import com.pnc.project.entities.Formulario;
import com.pnc.project.entities.Registro_Hora;
import com.pnc.project.entities.Usuario;

import java.util.List;

public class Registro_HoraMapper {
    public static Registro_Hora toEntity(Registro_HoraResponse registroHoraDTO) {
        return Registro_Hora.builder()
                .idRegistroHora(registroHoraDTO.getIdRegistro())
                .fechaRegistro(registroHoraDTO.getFechaRegistro())
                .horaInicio(registroHoraDTO.getHoraInicio())
                .horaFin(registroHoraDTO.getHoraFin())
                .horasEfectivas(registroHoraDTO.getHorasEfectivas())
                .aula(registroHoraDTO.getAula())
                .usuario(Usuario.builder().codigoUsuario(registroHoraDTO.getCodigoUsuario()).build())
                .actividad(Actividad.builder().actividadNombre(registroHoraDTO.getNombreActividad()).build())
                .formulario(Formulario.builder().idFormulario(registroHoraDTO.getIdFormulario()).build())
                .build();
    }

    public static Registro_Hora toEntityCreate(Registro_HoraRequest registroHoraDTO, Usuario usuario, Actividad actividad, Formulario formulario) {
        return Registro_Hora.builder()
                .fechaRegistro(registroHoraDTO.getFechaRegistro())
                .horaInicio(registroHoraDTO.getHoraInicio())
                .horaFin(registroHoraDTO.getHoraFin())
                .horasEfectivas(registroHoraDTO.getHorasEfectivas())
                .aula(registroHoraDTO.getAula())
                .usuario(usuario)
                .actividad(actividad)
                .formulario(formulario)
                .build();
    }

    public static Registro_HoraResponse toDTO(Registro_Hora registroHora) {
        return Registro_HoraResponse.builder()
                .idRegistro(registroHora.getIdRegistroHora())
                .fechaRegistro(registroHora.getFechaRegistro())
                .horaInicio(registroHora.getHoraInicio())
                .horaFin(registroHora.getHoraFin())
                .horasEfectivas(registroHora.getHorasEfectivas())
                .aula(registroHora.getAula())
                .codigoUsuario(registroHora.getUsuario().getCodigoUsuario())
                .nombreActividad(registroHora.getActividad().getActividadNombre())
                .idFormulario(registroHora.getFormulario().getIdFormulario())
                .build();
    }

    public static List<Registro_HoraResponse> toDTOList(List<Registro_Hora> registroHoras) {
        return registroHoras.stream()
                .map(Registro_HoraMapper::toDTO)
                .toList();
    }
}
