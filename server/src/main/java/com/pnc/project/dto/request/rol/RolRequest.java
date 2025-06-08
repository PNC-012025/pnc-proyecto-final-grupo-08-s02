package com.pnc.project.dto.request.rol;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RolRequest {
    @NotNull(message = "El ID del rol no puede ser nulo")
    @NotBlank(message = "El ID del rol no puede estar vacío")
    private String nombreRol;
}
