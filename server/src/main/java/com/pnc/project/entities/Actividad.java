package com.pnc.project.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "actividad")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Actividad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_actividad")
    private Integer idActividad;

    @Column(name = "actividad_nombre")
    private String actividadNombre;

    @Column(name = "tipo_actividad")
    private String tipoActividad;
}
