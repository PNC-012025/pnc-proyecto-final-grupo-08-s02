package com.pnc.project.utils.mappers;

import com.pnc.project.dto.request.usuario.UsuarioRequest;
import com.pnc.project.dto.response.usuario.UsuarioResponse;
import com.pnc.project.entities.Rol;
import com.pnc.project.entities.Usuario;

import java.util.List;

public class UsuarioMapper {
    public static Usuario toEntity(UsuarioResponse usuarioDTO) {
        return Usuario.builder()
                .idUsuario(usuarioDTO.getIdUsuario())
                .nombre(usuarioDTO.getNombre())
                .apellido(usuarioDTO.getApellido())
                .email(usuarioDTO.getCorreo())
                .rol(Rol.builder().nombreRol(usuarioDTO.getNombre()).build())
                .build();
    }

    public static Usuario toEntityCreate(UsuarioRequest usuarioDTO, Rol rol) {
        return Usuario.builder()
                .codigoUsuario(usuarioDTO.getCodigoUsuario())
                .nombre(usuarioDTO.getNombre())
                .apellido(usuarioDTO.getApellido())
                .email(usuarioDTO.getCorreo())
                .rol(rol)
                .build();
    }

    public static UsuarioResponse toDTO(Usuario usuario) {
        return UsuarioResponse.builder()
                .idUsuario(usuario.getIdUsuario())
                .codigoUsuario(usuario.getCodigoUsuario())
                .nombre(usuario.getNombre())
                .apellido(usuario.getApellido())
                .correo(usuario.getEmail())
                .rol(usuario.getRol().getNombreRol())
                .build();
    }

    public static List<UsuarioResponse> toDTOList(List<Usuario> usuarios) {
        return usuarios.stream()
                .map(UsuarioMapper::toDTO)
                .toList();
    }
}
