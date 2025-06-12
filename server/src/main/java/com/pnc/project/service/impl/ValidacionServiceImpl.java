package com.pnc.project.service.impl;

import com.pnc.project.dto.request.validacion.ValidacionRequest;
import com.pnc.project.dto.response.formulario.FormularioResponse;
import com.pnc.project.dto.response.usuario.UsuarioResponse;
import com.pnc.project.dto.response.validacion.ValidacionResponse;
import com.pnc.project.entities.Formulario;
import com.pnc.project.entities.Usuario;
import com.pnc.project.entities.Validacion;
import com.pnc.project.repository.ValidacionRepository;
import com.pnc.project.service.FormularioService;
import com.pnc.project.service.UsuarioService;
import com.pnc.project.service.ValidacionService;
import com.pnc.project.utils.mappers.FormularioMapper;
import com.pnc.project.utils.mappers.UsuarioMapper;
import com.pnc.project.utils.mappers.ValidacionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidacionServiceImpl implements ValidacionService {

    private final ValidacionRepository validacionRepository;
    private final UsuarioService usuarioService;
    private final FormularioService formularioService;

    @Autowired
    public ValidacionServiceImpl(ValidacionRepository validacionRepository, UsuarioService usuarioService, FormularioService formularioService) {
        this.validacionRepository = validacionRepository;
        this.usuarioService = usuarioService;
        this.formularioService = formularioService;
    }

    @Override
    public ValidacionResponse save(ValidacionRequest validacion) {
        UsuarioResponse usuarioDto = usuarioService.findByCodigo(validacion.getCodigoUsuario());
        FormularioResponse formularioDto = formularioService.findById(validacion.getIdFormulario());

        Usuario usuario = UsuarioMapper.toEntity(usuarioDto);
        Formulario formulario = FormularioMapper.toEntity(formularioDto);


        return ValidacionMapper.toDTO(validacionRepository.save(ValidacionMapper.toEntityCreate(validacion, usuario, formulario)));
    }

    @Override
    public ValidacionResponse findByFormulario(Formulario formulario) {
        Validacion validacion = validacionRepository.findByFormulario(formulario)
                .orElseThrow(() -> new RuntimeException("No se encontró validación para el formulario"));

        return ValidacionMapper.toDTO(validacion);
    }

    @Override
    public ValidacionResponse update(ValidacionRequest validacion) {
        Validacion existente = validacionRepository.findById(validacion.getIdValidacion())
                .orElseThrow(() -> new RuntimeException("Validación no encontrada con ID: " + validacion.getIdValidacion()));

        UsuarioResponse usuarioDto = usuarioService.findByCodigo(validacion.getCodigoUsuario());
        FormularioResponse formularioDto = formularioService.findById(validacion.getIdFormulario());

        Usuario usuario = UsuarioMapper.toEntity(usuarioDto);
        Formulario formulario = FormularioMapper.toEntity(formularioDto);

        existente.setUsuario(usuario);
        existente.setFormulario(formulario);
        existente.setEstadoValidacion(validacion.getEstadoValidacion());
        existente.setFechaValidacion(validacion.getFeachaValidacion());

        return ValidacionMapper.toDTO(validacionRepository.save(existente));
    }


}
