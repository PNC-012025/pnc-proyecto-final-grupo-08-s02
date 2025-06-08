package com.pnc.project.repository;

import com.pnc.project.entities.Materia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MateriaRepository extends JpaRepository<Materia, Integer> {
    Optional<Materia> findByNombreMateria(String nombreMateria);
}
