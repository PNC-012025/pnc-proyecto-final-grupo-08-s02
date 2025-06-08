package com.pnc.project.utils.mappers;

import com.pnc.project.dto.request.rol.RolRequest;
import com.pnc.project.dto.response.rol.RolResponse;
import com.pnc.project.entities.Rol;

import java.util.List;

public class RolMapper {
    public static Rol toEntity(RolResponse rolDTO){
        return Rol.builder()
                .idRol(rolDTO.getIdRol())
                .nombreRol(rolDTO.getNombreRol())
                .build();
    }

    public static Rol toEntityCreate(RolRequest rolDTO) {
        return Rol.builder()
                .nombreRol(rolDTO.getNombreRol())
                .build();
    }

    public static RolResponse toDTO(Rol rol) {
        return RolResponse.builder()
                .idRol(rol.getIdRol())
                .nombreRol(rol.getNombreRol())
                .build();
    }

    public static List<RolResponse> toDTOList(List<Rol> rols) {
        return rols.stream()
                .map(RolMapper::toDTO)
                .toList();
    }
}
