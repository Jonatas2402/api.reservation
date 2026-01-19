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

    public HospedeModel salva(UsuarioModel usuarioModel, HospedeModel hospedeModel){
            UsuarioModel buscaUsuario = usuarioRepository.findById(usuarioModel.getId())
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

            hospedeModel.setUsuario(buscaUsuario);
            return hospedeRepository.save(hospedeModel);
    }

    public List<HospedeModel> listandoHospedesPorIdDoUsuario(UUID id){
        return hospedeRepository.findByUsuarioId(id);
    }
    public List<HospedeModel> listaTodosOsHospedes() {
        return hospedeRepository.findAll();
    }

    public HospedeModel atualizaDadosHospede(HospedeModel buscaId, HospedeModel hospede) {
        HospedeModel hospedeAtualizado = hospedeRepository.findById(buscaId.getId())
                .orElseThrow(() -> new RuntimeException("Hospede não encontrado"));

        hospedeAtualizado.setNome(hospede.getNome());
        hospedeAtualizado.setEmail(hospede.getEmail());
        hospedeAtualizado.setTelefone(hospede.getTelefone());

        return hospedeRepository.save(hospedeAtualizado);
    }
    public void excluiHospede(HospedeModel hospedeModel) {
        if (!hospedeRepository.existsById(hospedeModel.getId())){
            throw new RuntimeException("Hospede não encontrado.");
        }
        hospedeRepository.deleteById(hospedeModel.getId());
    }
}
