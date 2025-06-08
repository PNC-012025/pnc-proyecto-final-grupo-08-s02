package com.pnc.project.dto.response.rol;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RolResponse {
    private Integer idRol;
    private String nombreRol;
}
