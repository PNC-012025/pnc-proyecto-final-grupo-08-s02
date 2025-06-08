package com.pnc.project.repository;

import com.pnc.project.entities.Registro_Hora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Registro_HoraRepository extends JpaRepository<Registro_Hora, Integer> {

}
