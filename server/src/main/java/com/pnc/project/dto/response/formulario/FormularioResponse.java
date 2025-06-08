package com.pnc.project.dto.response.formulario;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class FormularioResponse {
    private Integer idFormulario;
    private LocalDate fechaCreacion;
    private String estado;
    private String codigoUsuario;
}
