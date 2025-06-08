package com.pnc.project.dto.response.registro_hora;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
public class Registro_HoraResponse {
    private Integer idRegistro;
    private LocalDate fechaRegistro;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private BigDecimal horasEfectivas;
    private String aula;
    private String codigoUsuario;
    private String nombreActividad;
    private Integer idFormulario;
}
