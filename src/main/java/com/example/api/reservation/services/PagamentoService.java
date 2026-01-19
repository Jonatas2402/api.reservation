package com.example.api.reservation.services;

import com.example.api.reservation.enuns.StatusPg;
import com.example.api.reservation.enuns.StatusReserva;
import com.example.api.reservation.models.PagamentoModel;
import com.example.api.reservation.models.ReservaModel;
import com.example.api.reservation.repositories.PagamentoRepository;
import com.example.api.reservation.repositories.ReservaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PagamentoService {
    private final PagamentoRepository pagamentoRepository;
    private final ReservaRepository reservaRepository;

    public PagamentoService(PagamentoRepository pagamentoRepository, ReservaRepository reservaRepository) {
        this.pagamentoRepository = pagamentoRepository;
        this.reservaRepository = reservaRepository;
    }

    public PagamentoModel confirmandoPagamento(ReservaModel reserva, PagamentoModel pagamento) {
        ReservaModel reservaId = reservaRepository.findById(reserva.getId())
                .orElseThrow(() -> new RuntimeException("Reserva não encontrada."));

        pagamento.setReserva(reserva);
        pagamento.setStatusPg(StatusPg.PAGO);
        //Confirmando reserva
        reserva.setStatusReserva(StatusReserva.CONFIRMADA);
        reservaRepository.save(reserva);

        return pagamentoRepository.save(pagamento);
    }

    public PagamentoModel buscarPagamentoPorId(UUID id) {
        return pagamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pagamento não encontrado."));
    }
    public List<PagamentoModel> todosOsPagamento() {
        return pagamentoRepository.findAll();
    }
}
