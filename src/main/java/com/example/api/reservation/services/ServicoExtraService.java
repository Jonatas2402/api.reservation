package com.example.api.reservation.services;

import com.example.api.reservation.models.ReservaModel;
import com.example.api.reservation.models.ServicoExtraModel;
import com.example.api.reservation.repositories.ReservaRepository;
import com.example.api.reservation.repositories.ServicoExtraRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicoExtraService {
    private final ServicoExtraRepository servicoExtraRepository;
    private final ReservaRepository reservaRepository;

    public ServicoExtraService(ServicoExtraRepository servicoExtraRepository, ReservaRepository reservaRepository) {
        this.servicoExtraRepository = servicoExtraRepository;
        this.reservaRepository = reservaRepository;
    }
    public ServicoExtraModel adicionandoServicoAUmaReserva(ReservaModel reserva, ServicoExtraModel servico){
        ReservaModel reservaId = reservaRepository.findById(reserva.getId())
                .orElseThrow(() -> new RuntimeException("Reserva não encontrada."));

        servico.setReserva(reservaId);
        return servicoExtraRepository.save(servico);
    }
    public List<ServicoExtraModel> lsitaServicoDeUmaReserva(ReservaModel reserva, ServicoExtraModel servico) {
        return servicoExtraRepository.findByReservaId(reserva.getId());
    }

    public void removeServico(ServicoExtraModel servico) {
        if (!servicoExtraRepository.existsById(servico.getId())){
            throw new RuntimeException("Serviço não encontrado");
        }
        servicoExtraRepository.deleteById(servico.getId());
    }
}
