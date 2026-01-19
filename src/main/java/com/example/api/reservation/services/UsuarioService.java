package com.example.api.reservation.services;

import com.example.api.reservation.models.UsuarioModel;
import com.example.api.reservation.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioModel criandoUsuario(UsuarioModel usuarioModel){
        //Verifica email.
        if (usuarioRepository.existsByEmail(usuarioModel.getEmail())){
            throw new RuntimeException("Usuário já cadastrado com esse email.");
        }
        return usuarioRepository.save(usuarioModel);
    }
    public UsuarioModel buscarUsuarioPorId(UUID id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }
    public List<UsuarioModel> listarTodosOsUsuarios() {
        return usuarioRepository.findAll();
    }

    public UsuarioModel atualizarUsuario(UsuarioModel buscaId, UsuarioModel usuarioModel) {
        UsuarioModel atualizaUsuario = buscarUsuarioPorId(buscaId.getId());

        atualizaUsuario.setNome(usuarioModel.getNome());
        atualizaUsuario.setEmail(usuarioModel.getEmail());

        return usuarioRepository.save(atualizaUsuario);
    }

}
