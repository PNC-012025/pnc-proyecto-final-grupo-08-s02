package com.pnc.project.dto.request.rol;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RolRequest {
    @NotNull(message = "El ID del rol no puede ser nulo")
    @NotBlank(message = "El ID del rol no puede estar vacío")
    @Pattern(regexp = "ENCARGADO|INSTRUCTOR_NORMAL|INSTRUCTOR_REMUNERADO", message = "Rol no válido")
    private String nombreRol;
}
