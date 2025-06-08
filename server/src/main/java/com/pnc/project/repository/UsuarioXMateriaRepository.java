package com.pnc.project.repository;

import com.pnc.project.entities.UsuarioXMateria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioXMateriaRepository extends JpaRepository<UsuarioXMateria, Integer> {
}
