package com.pnc.project.repository;

import com.pnc.project.entities.Formulario;
import com.pnc.project.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface FormularioRepository extends JpaRepository<Formulario, Integer> {
    Optional<Formulario> findByFechaCreacion(LocalDate fechaCreacion);
    Optional<Formulario> findByEstado(String estado);
    List<Formulario> findByUsuario(Usuario usuario);
}
