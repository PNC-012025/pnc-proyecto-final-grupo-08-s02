package com.pnc.project.repository;

import com.pnc.project.entities.Validacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ValidaciónRepository extends JpaRepository<Validacion, Integer> {
}
