package com.pnc.project.controllers;

import com.pnc.project.config.JwtConfig;
import com.pnc.project.dto.Response;
import com.pnc.project.dto.request.usuario.Login;
import com.pnc.project.dto.request.usuario.UsuarioRequest;
import com.pnc.project.dto.response.usuario.UsuarioResponse;
import com.pnc.project.entities.Materia;
import com.pnc.project.entities.Rol;
import com.pnc.project.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final JwtConfig  jwt;

    // Listar todos los usuarios
    @GetMapping("/usuarios/list")
    public ResponseEntity<List<UsuarioResponse>> findAll() {
        List<UsuarioResponse> usuarios = usuarioService.findAll();
        return ResponseEntity.ok(usuarios);
    }

    // Listar usuarios por materia
    @GetMapping("/usuarios/materia")
    public ResponseEntity<List<UsuarioResponse>> findByMateria(@RequestParam("idMateria") int idMateria) {
        List<UsuarioResponse> usuarios = usuarioService.findByMateriaId(idMateria);
        return ResponseEntity.ok(usuarios);
    }

    // Obtener usuario por ID
    @GetMapping("/usuarios/data/{id}")
    public ResponseEntity<UsuarioResponse> findById(@PathVariable int id) {
        UsuarioResponse usuario = usuarioService.findById(id);
        return ResponseEntity.ok(usuario);
    }

    // Obtener usuario por código
    @GetMapping("/usuarios/codigo/{codigo}")
    public ResponseEntity<UsuarioResponse> findByCodigo(@PathVariable String codigo) {
        UsuarioResponse usuario = usuarioService.findByCodigo(codigo);
        return ResponseEntity.ok(usuario);
    }

    // Obtener usuario por rol
    @GetMapping("/usuarios/rol")
    public ResponseEntity<List<UsuarioResponse>> findByRol(@RequestParam("idRol") int idRol) {
        List<UsuarioResponse> usuario = usuarioService.findByRolId(idRol);
        return ResponseEntity.ok(usuario);
    }

    // Crear un usuario
    @PostMapping("/save")
    public ResponseEntity<UsuarioResponse> save(@RequestBody UsuarioRequest usuarioRequest) {
        UsuarioResponse usuario = usuarioService.save(usuarioRequest);
        return ResponseEntity.ok(usuario);
    }

    // Actualizar un usuario
    @PutMapping("/usuarios/update/{id}")
    public ResponseEntity<UsuarioResponse> update(@PathVariable int id, @RequestBody UsuarioRequest usuarioRequest) {
        usuarioRequest.setIdUsuario(id);
        UsuarioResponse usuarioActualizado = usuarioService.update(usuarioRequest);
        return ResponseEntity.ok(usuarioActualizado);
    }

    // Eliminar un usuario por su ID
    @DeleteMapping("/usuarios/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Login de usuario
    @PostMapping("/auth/login")
    public ResponseEntity<Object> login(@Valid @RequestBody  Login body) {
        UsuarioResponse user = usuarioService.login(body.getEmail(), body.getPassword());
        if(user != null){
            String token = jwt.createToken(user);
            return Response.build(HttpStatus.ACCEPTED.value(), "welcome " + user.getNombre(), token);
        }else{
            return Response.build(HttpStatus.UNAUTHORIZED.value(), "email or password incorrect", null);
        }
    }
}