package com.pnc.project.controllers;

import com.pnc.project.dto.request.usuario.UsuarioRequest;
import com.pnc.project.dto.response.usuario.UsuarioResponse;
import com.pnc.project.entities.Materia;
import com.pnc.project.entities.Rol;
import com.pnc.project.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    // Listar todos los usuarios
    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> findAll() {
        List<UsuarioResponse> usuarios = usuarioService.findAll();
        return ResponseEntity.ok(usuarios);
    }

    // Listar usuarios por materia
    @GetMapping("/materia")
    public ResponseEntity<List<UsuarioResponse>> findByMateria(@RequestParam Materia materia) {
        List<UsuarioResponse> usuarios = usuarioService.findByMateria(materia);
        return ResponseEntity.ok(usuarios);
    }

    // Obtener usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> findById(@PathVariable int id) {
        UsuarioResponse usuario = usuarioService.findById(id);
        return ResponseEntity.ok(usuario);
    }

    // Obtener usuario por código
    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<UsuarioResponse> findByCodigo(@PathVariable String codigo) {
        UsuarioResponse usuario = usuarioService.findByCodigo(codigo);
        return ResponseEntity.ok(usuario);
    }

    // Obtener usuario por rol
    @GetMapping("/rol")
    public ResponseEntity<UsuarioResponse> findByRol(@RequestParam Rol rol) {
        UsuarioResponse usuario = usuarioService.findByRol(rol);
        return ResponseEntity.ok(usuario);
    }

    // Crear un usuario
    @PostMapping
    public ResponseEntity<UsuarioResponse> save(@RequestBody UsuarioRequest usuarioRequest) {
        UsuarioResponse usuario = usuarioService.save(usuarioRequest);
        return ResponseEntity.ok(usuario);
    }

    // Actualizar un usuario
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponse> update(@PathVariable int id, @RequestBody UsuarioRequest usuarioRequest) {
        usuarioRequest.setIdUsuario(id);
        UsuarioResponse usuarioActualizado = usuarioService.update(usuarioRequest);
        return ResponseEntity.ok(usuarioActualizado);
    }

    // Eliminar un usuario por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Login de usuario
    @PostMapping("/login")
    public ResponseEntity<UsuarioResponse> login(@RequestParam String email, @RequestParam String password) {
        UsuarioResponse usuario = usuarioService.login(email, password);
        return ResponseEntity.ok(usuario);
    }
}