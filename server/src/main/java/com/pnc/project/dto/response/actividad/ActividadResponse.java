package com.pnc.project.dto.response.actividad;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ActividadResponse {
    private Integer idActividad;
    private String nombreActividad;
    private String tipoActividad;
}
