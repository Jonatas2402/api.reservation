package com.example.api.reservation.services;

import com.example.api.reservation.models.HospedeModel;
import com.example.api.reservation.models.UsuarioModel;
import com.example.api.reservation.repositories.HospedeRepository;
import com.example.api.reservation.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HospedeService {

    private final UsuarioRepository usuarioRepository;

    private final HospedeRepository hospedeRepository;

    public HospedeService(UsuarioRepository usuarioRepository, HospedeRepository hospedeRepository) {
        this.usuarioRepository = usuarioRepository;
        this.hospedeRepository = hospedeRepository;
    }
    public HospedeModel salva(UUID id, HospedeModel hospedeModel){
            UsuarioModel buscaUsuario = usuarioRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

            hospedeModel.setUsuario(buscaUsuario);
            return hospedeRepository.save(hospedeModel);
    }
    public List<HospedeModel> listandoHospedesPorIdDoUsuario(UUID id){
        return hospedeRepository.findByUsarioId(id);
    }
}
