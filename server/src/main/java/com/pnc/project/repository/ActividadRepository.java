package com.pnc.project.repository;

import com.pnc.project.entities.Actividad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ActividadRepository extends JpaRepository<Actividad, Integer> {
    Optional<Actividad> findByNombreActividad(String nombreActividad);
}
