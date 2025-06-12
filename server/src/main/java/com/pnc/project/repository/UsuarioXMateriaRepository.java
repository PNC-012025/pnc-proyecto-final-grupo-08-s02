package com.pnc.project.repository;

import com.pnc.project.entities.Materia;
import com.pnc.project.entities.Usuario;
import com.pnc.project.entities.UsuarioXMateria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioXMateriaRepository extends JpaRepository<UsuarioXMateria, Integer> {
    List<UsuarioXMateria> findByMateria(Materia materia);
    List<UsuarioXMateria> findByUsuario(Usuario usuario);
    List<UsuarioXMateria> findByMateria_IdMateria(Integer idMateria);
}
