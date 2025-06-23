package com.pnc.project.dto.request.actividad;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ActividadRequest {

    private Integer idActividad;

    @NotNull(message = "No puedes enviar un nombre nulo")
    @NotBlank(message = "El nombre de la actividad no puede estar vacío")
    private String nombreActividad;

    @NotNull(message = "No puedes enviar un tipo nulo")
    @NotBlank(message = "El tipo de la actividad no puede estar vacío")
    private String tipoActividad;
}
