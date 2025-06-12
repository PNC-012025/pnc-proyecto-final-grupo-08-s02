package com.pnc.project.repository;

import com.pnc.project.entities.Formulario;
import com.pnc.project.entities.Validacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ValidacionRepository extends JpaRepository<Validacion, Integer> {
    Optional<Validacion> findByFormulario(Formulario formulario);
}
