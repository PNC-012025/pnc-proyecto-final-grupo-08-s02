package com.pnc.project.controllers;

import com.pnc.project.dto.request.usuarioxmateria.UsuarioXMateriaRequest;
import com.pnc.project.dto.response.usuarioxmateria.UsuarioXMateriaResponse;
import com.pnc.project.entities.Materia;
import com.pnc.project.entities.Usuario;
import com.pnc.project.service.UsuarioXMateriaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuario-materias")
@RequiredArgsConstructor
public class UsuarioXMateriaController {

    private final UsuarioXMateriaService usuarioXMateriaService;

    /**
     * Asignar un usuario a una materia.
     *
     * @param usuarioXMateriaRequest DTO que contiene la información del usuario y la materia.
     * @return Respuesta con los datos de la asignación.
     */
    @PostMapping
    public ResponseEntity<UsuarioXMateriaResponse> save(@Valid @RequestBody UsuarioXMateriaRequest usuarioXMateriaRequest) {
        UsuarioXMateriaResponse asignacion = usuarioXMateriaService.save(usuarioXMateriaRequest);
        return ResponseEntity.ok(asignacion);
    }

    /**
     * Eliminar la asociación entre un usuario y una materia.
     *
     * @param idUsuarioXMateria ID de la asociación a eliminar.
     * @return Respuesta sin contenido.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int idUsuarioXMateria) {
        usuarioXMateriaService.delete(idUsuarioXMateria);
        return ResponseEntity.noContent().build();
    }

    /**
     * Listar todos los usuarios asignados a una materia específica.
     *
     * @param idMateria ID de la materia.
     * @return Lista de usuarios asignados a la materia.
     */
    @GetMapping("/materia/{idMateria}")
    public ResponseEntity<List<UsuarioXMateriaResponse>> listUserMaterias(@PathVariable("idMateria") int idMateria) {
        Materia materia = Materia.builder().idMateria(idMateria).build(); // Construcción de Materia básica.
        List<UsuarioXMateriaResponse> usuarios = usuarioXMateriaService.listUserMaterias(materia);
        return ResponseEntity.ok(usuarios);
    }

    /**
     * Listar todas las materias asignadas a un usuario específico.
     *
     * @param idUsuario ID del usuario.
     * @return Lista de materias asignadas al usuario.
     */
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<UsuarioXMateriaResponse>> listMateriaUser(@PathVariable("idUsuario") int idUsuario) {
        Usuario usuario = Usuario.builder().idUsuario(idUsuario).build(); // Construcción de Usuario básico.
        List<UsuarioXMateriaResponse> materias = usuarioXMateriaService.listMateriaUser(usuario);
        return ResponseEntity.ok(materias);
    }
}