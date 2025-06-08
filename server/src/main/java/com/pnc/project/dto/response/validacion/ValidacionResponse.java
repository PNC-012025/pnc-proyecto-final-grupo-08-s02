package com.pnc.project.dto.response.validacion;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ValidacionResponse {
    private Integer idValidacion;
    private LocalDate feachaValidacion;
    private Boolean estadoValidacion;
    private String codigoUsuario;
    private Integer idFormulario;
}
