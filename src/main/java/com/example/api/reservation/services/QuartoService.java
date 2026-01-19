package com.example.api.reservation.services;

import com.example.api.reservation.enuns.StatusQuarto;
import com.example.api.reservation.models.QuartoModel;
import com.example.api.reservation.repositories.QuartoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class QuartoService {
    private final QuartoRepository quartoRepository;

    public QuartoService(QuartoRepository quartoRepository) {
        this.quartoRepository = quartoRepository;
    }

    public QuartoModel criaQuarto(QuartoModel quartoModel) {
        if (quartoRepository.existsByNumeroQuarto(quartoModel.getNumeroQuarto())){
            throw new RuntimeException("Já existe um quarto com esse número.");
        }
        quartoModel.setStatusQuarto(StatusQuarto.LIVRE);
        return quartoRepository.save(quartoModel);
    }

    public List<QuartoModel> buscarTodosOsQuartos() {
        return quartoRepository.findAll();
    }

    public QuartoModel buscarQuartoPorId(UUID id) {
        return quartoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Quarto não encontrado."));
    }
    public QuartoModel atualizaQuarto(QuartoModel quartoModel) {
        QuartoModel quartoAtualizado = quartoRepository.findById(quartoModel.getId())
                .orElseThrow(() -> new RuntimeException("Quarto não encontrado."));

        quartoAtualizado.setTipoQuarto(quartoModel.getTipoQuarto());
        quartoAtualizado.setCapacidade(quartoModel.getCapacidade());
        quartoAtualizado.setStatusQuarto(quartoModel.getStatusQuarto());
        quartoAtualizado.setPrecoDiaria(quartoModel.getPrecoDiaria());

        return quartoRepository.save(quartoAtualizado);
    }

    public QuartoModel bloqueiaQuarto(UUID id) {
            QuartoModel quartoBloqueado = buscarQuartoPorId(id);
            quartoBloqueado.setStatusQuarto(StatusQuarto.OCUPADO);
            quartoBloqueado.setStatusQuarto(StatusQuarto.MANUTENCAO);

            return quartoRepository.save(quartoBloqueado);

    }

    public QuartoModel liberandoQuarto(UUID id) {
        QuartoModel quartoLiberado = buscarQuartoPorId(id);
        quartoLiberado.setStatusQuarto(StatusQuarto.LIVRE);
        return quartoRepository.save(quartoLiberado);
    }
}
