package com.pnc.project.controllers;

import com.pnc.project.utils.enums.RolNombre;
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

    @GetMapping
    public ResponseEntity<List<RolResponse>> findAll() {
        List<RolResponse> roles = rolService.findAll();
        return ResponseEntity.ok(roles);
    }

    @GetMapping("/{name}")
    public ResponseEntity<RolResponse> findByName(@PathVariable("name") String name) {
        RolResponse rol = rolService.findByName(RolNombre.valueOf(name).toString());
        return ResponseEntity.ok(rol);
    }

    @PostMapping
    public ResponseEntity<RolResponse> save(@Valid @RequestBody RolRequest rolRequest) {
        throw new UnsupportedOperationException("Operación no soportada: los roles son fijos.");
    }

}