package com.pnc.project.dto.request.registro_hora;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
public class Registro_HoraRequest {
    @NotNull(message = "La fecha de registro no puede ser nula")
    private LocalDate fechaRegistro;

    @NotNull(message = "La hora de inicio no puede ser nula")
    private LocalTime horaInicio;

    @NotNull(message = "La hora de fin no puede ser nula")
    private LocalTime horaFin;

    @NotNull(message = "Las horas efectivas no pueden ser nulas")
    private BigDecimal horasEfectivas;

    @NotNull(message = "El aula no puede ser nula")
    private String aula;

    @NotNull(message = "El código de usuario no puede ser nulo")
    private String codigoUsuario;

    @NotNull(message = "El nombre de actividad no puede ser nulo")
    private String nombreActividad;

    @NotNull(message = "El id del formulario no puede ser nulo")
    private Integer idFormulario;
}
