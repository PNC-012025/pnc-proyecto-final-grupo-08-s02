package com.pnc.project.controllers;

import com.pnc.project.dto.request.rol.RolRequest;
import com.pnc.project.dto.response.rol.RolResponse;
import com.pnc.project.service.RolService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RolController {

    private final RolService rolService;

    /**
     * Obtener todos los roles disponibles.
     *
     * @return Lista de roles en formato de respuesta.
     */
    @GetMapping
    public ResponseEntity<List<RolResponse>> findAll() {
        List<RolResponse> roles = rolService.findAll();
        return ResponseEntity.ok(roles);
    }

    /**
     * Obtener un rol por su nombre.
     *
     * @param name Nombre del rol.
     * @return Rol encontrado.
     */
    @GetMapping("/{name}")
    public ResponseEntity<RolResponse> findByName(@PathVariable("name") String name) {
        RolResponse rol = rolService.findByName(name);
        return ResponseEntity.ok(rol);
    }

    /**
     * Crear un nuevo rol.
     *
     * @param rolRequest DTO con los datos del nuevo rol.
     * @return Rol creado.
     */
    @PostMapping
    public ResponseEntity<RolResponse> save(@Valid @RequestBody RolRequest rolRequest) {
        throw new UnsupportedOperationException("Operación no soportada: los roles son fijos.");
    }

}