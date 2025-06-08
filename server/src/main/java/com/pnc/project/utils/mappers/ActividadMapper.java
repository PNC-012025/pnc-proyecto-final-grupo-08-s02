package com.pnc.project.utils.mappers;

import com.pnc.project.dto.request.actividad.ActividadRequest;
import com.pnc.project.dto.response.actividad.ActividadResponse;
import com.pnc.project.entities.Actividad;

import java.util.List;

public class ActividadMapper {
    public static Actividad toEntity(ActividadResponse actividadDTO){
        return Actividad.builder()
                .idActividad(actividadDTO.getIdActividad())
                .actividadNombre(actividadDTO.getNombreActividad())
                .tipoActividad(actividadDTO.getTipoActividad())
                .build();
    }

    public static Actividad toEntityCreate(ActividadRequest actividadDTO) {
        return Actividad.builder()
                .actividadNombre(actividadDTO.getNombreActividad())
                .tipoActividad(actividadDTO.getTipoActividad())
                .build();
    }

    public static ActividadResponse toDTO(Actividad actividad) {
        return ActividadResponse.builder()
                .idActividad(actividad.getIdActividad())
                .nombreActividad(actividad.getActividadNombre())
                .tipoActividad(actividad.getTipoActividad())
                .build();
    }

    public static List<ActividadResponse> toDTOList(List<Actividad> actividades) {
        return actividades.stream()
                .map(ActividadMapper::toDTO)
                .toList();
    }
}
