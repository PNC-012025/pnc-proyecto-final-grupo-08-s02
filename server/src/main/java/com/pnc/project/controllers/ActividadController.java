package com.pnc.project.controllers;

import com.pnc.project.dto.request.actividad.ActividadRequest;
import com.pnc.project.dto.response.actividad.ActividadResponse;
import com.pnc.project.service.ActividadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/actividades")
@RequiredArgsConstructor
public class ActividadController {

    private final ActividadService actividadService;

    // Listar todas las actividades
    @GetMapping
    public ResponseEntity<List<ActividadResponse>> findAll() {
        List<ActividadResponse> actividades = actividadService.findAll();
        return ResponseEntity.ok(actividades);
    }

    // Listar actividades por tipo
    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<ActividadResponse>> findByTipo(@PathVariable String tipo) {
        List<ActividadResponse> actividades = actividadService.findByTipo(tipo);
        return ResponseEntity.ok(actividades);
    }

    // Buscar actividad por ID
    @GetMapping("/{id}")
    public ResponseEntity<ActividadResponse> findById(@PathVariable int id) {
        ActividadResponse actividad = actividadService.findById(id);
        if (actividad == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actividad);
    }

    // Crear actividad
    @PostMapping
    public ResponseEntity<ActividadResponse> save(@RequestBody ActividadRequest actividadRequest) {
        ActividadResponse actividad = actividadService.save(actividadRequest);
        return ResponseEntity.ok(actividad);
    }

    // Actualizar actividad
    @PutMapping("/{id}")
    public ResponseEntity<ActividadResponse> update(@PathVariable int id, @RequestBody ActividadRequest actividadRequest) {
        actividadRequest.setIdActividad(id); // Asegurarse de que el ID esté adecuado
        ActividadResponse actividadActualizada = actividadService.update(actividadRequest);
        return ResponseEntity.ok(actividadActualizada);
    }

    // Eliminar actividad por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        actividadService.delete(id);
        return ResponseEntity.noContent().build();
    }
}