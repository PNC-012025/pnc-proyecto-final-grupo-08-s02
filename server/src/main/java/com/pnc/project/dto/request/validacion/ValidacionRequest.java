package com.pnc.project.dto.request.validacion;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ValidacionRequest {
    @NotNull(message = "ID de validación no puede ser nulo")
    private Integer idValidacion;

    @NotNull(message = "Fecha de validación no puede ser nula")
    private LocalDate feachaValidacion;

    @NotNull(message = "Estado de validación no puede ser nulo")
    private Boolean estadoValidacion;

    @NotNull(message = "Código de usuario no puede ser nulo")
    private String codigoUsuario;

    @NotNull(message = "ID del formulario no puede ser nulo")
    private Integer idFormulario;
}
