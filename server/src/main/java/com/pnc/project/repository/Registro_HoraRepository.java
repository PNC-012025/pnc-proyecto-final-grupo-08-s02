package com.pnc.project.repository;

import com.pnc.project.entities.Formulario;
import com.pnc.project.entities.Registro_Hora;
import com.pnc.project.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface Registro_HoraRepository extends JpaRepository<Registro_Hora, Integer> {

    List<Registro_Hora> findByUsuario(Usuario usuario);
    List<Registro_Hora> findByFormulario(Formulario formulario);
    List<Registro_Hora> findByFechaRegistroBetween(LocalDate inicio, LocalDate fin);
    List<Registro_Hora> findByUsuarioAndFechaRegistroBetween(Usuario usuario, LocalDate inicio, LocalDate fin);


}
