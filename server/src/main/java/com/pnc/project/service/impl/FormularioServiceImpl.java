package com.pnc.project.service.impl;

import com.pnc.project.dto.request.formulario.FormularioRequest;
import com.pnc.project.dto.response.formulario.FormularioResponse;
import com.pnc.project.dto.response.usuario.UsuarioResponse;
import com.pnc.project.entities.Usuario;
import com.pnc.project.repository.FormularioRepository;
import com.pnc.project.service.FormularioService;
import com.pnc.project.service.UsuarioService;
import com.pnc.project.utils.mappers.FormularioMapper;
import com.pnc.project.utils.mappers.UsuarioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class FormularioServiceImpl implements FormularioService {
    private final FormularioRepository formularioRepository;
    private final UsuarioService usuarioService;

    @Autowired
    public FormularioServiceImpl(FormularioRepository formularioRepository, UsuarioService usuarioService) {
        this.formularioRepository= formularioRepository;
        this.usuarioService = usuarioService;
    }

    @Override
    public List<FormularioResponse> findAllUsers(Usuario usuario) {
        return FormularioMapper.toDTOList(formularioRepository.findByUsuario(usuario));
    }

    @Override
    public FormularioResponse findById(int id) {
        return FormularioMapper.toDTO(formularioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Formulario not found")));
    }

    @Override
    public FormularioResponse save(FormularioRequest formulario) {
        UsuarioResponse usuario = usuarioService.findByCodigo(formulario.getCodigoUsuario());
        return FormularioMapper.toDTO(formularioRepository.save(FormularioMapper.toEntityCreate(formulario, UsuarioMapper.toEntity(usuario))));
    }

    @Override
    public FormularioResponse update(FormularioRequest formulario) {
        UsuarioResponse usuario = usuarioService.findByCodigo(formulario.getCodigoUsuario());
        return FormularioMapper.toDTO(formularioRepository.save(FormularioMapper.toEntityUpdate(formulario, UsuarioMapper.toEntity(usuario))));
    }

    @Override
    public void delete(int id) {formularioRepository.deleteById(id);}

}
