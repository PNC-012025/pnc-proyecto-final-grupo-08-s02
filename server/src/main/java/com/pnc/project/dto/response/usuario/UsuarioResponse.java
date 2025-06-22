package com.pnc.project.dto.response.usuario;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioResponse {
    private int idUsuario;
    private String codigoUsuario;
    private String nombre;
    private String apellido;
    private String correo;
    private String contrasena;
    private String rol;
}
