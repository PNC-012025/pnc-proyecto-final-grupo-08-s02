package com.pnc.project.controllers;

import com.pnc.project.dto.request.validacion.ValidacionRequest;
import com.pnc.project.dto.response.formulario.FormularioResponse;
import com.pnc.project.dto.response.validacion.ValidacionResponse;
import com.pnc.project.service.ValidacionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/validaciones")
@RequiredArgsConstructor
public class ValidacionController {

    private final ValidacionService validacionService;

    /**
     * Crear una nueva validación.
     *
     * @param validacionRequest DTO con la información de la validación.
     * @return Respuesta con la validación creada.
     */
    @PostMapping
    public ResponseEntity<ValidacionResponse> save(@Valid @RequestBody ValidacionRequest validacionRequest) {
        ValidacionResponse validacion = validacionService.save(validacionRequest);
        return ResponseEntity.ok(validacion);
    }

    /**
     * Actualizar una validación existente.
     *
     * @param validacionRequest DTO con la información actualizada de la validación.
     * @return Respuesta con la validación actualizada.
     */
    @PutMapping
    public ResponseEntity<ValidacionResponse> update(@Valid @RequestBody ValidacionRequest validacionRequest) {
        ValidacionResponse validacionActualizada = validacionService.update(validacionRequest);
        return ResponseEntity.ok(validacionActualizada);
    }

    /**
     * Obtener los formularios pendientes para el encargado.
     *
     * @return Lista con los formularios pendientes.
     */
    @GetMapping("/formularios-pendientes")
    public ResponseEntity<List<FormularioResponse>> listarFormulariosPendientes() {
        List<FormularioResponse> formulariosPendientes = validacionService.listarFormulariosPendientes();
        return ResponseEntity.ok(formulariosPendientes);
    }

    /**
     * Listar las validaciones asignadas a un encargado por su ID.
     *
     * @param idEncargado ID del encargado.
     * @return Lista con las validaciones del encargado.
     */
    @GetMapping("/encargado/{idEncargado}")
    public ResponseEntity<List<ValidacionResponse>> listarValidacionesPorEncargado(@PathVariable Integer idEncargado) {
        List<ValidacionResponse> validaciones = validacionService.listarValidacionesPorEncargado(idEncargado);
        return ResponseEntity.ok(validaciones);
    }

    /**
     * Rechazar un formulario.
     *
     * @param idFormulario ID del formulario a rechazar.
     * @param observacion  Observación del rechazo.
     * @return Respuesta sin contenido si la operación fue exitosa.
     */
    @PostMapping("/rechazar")
    public ResponseEntity<Void> rechazarFormulario(
            @RequestParam Integer idFormulario,
            @RequestParam String observacion
    ) {
        validacionService.rechazarFormulario(idFormulario, observacion);
        return ResponseEntity.noContent().build();
    }
}