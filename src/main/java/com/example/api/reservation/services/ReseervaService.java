package com.example.api.reservation.services;

import com.example.api.reservation.enuns.StatusReserva;
import com.example.api.reservation.models.HospedeModel;
import com.example.api.reservation.models.QuartoModel;
import com.example.api.reservation.models.ReservaModel;
import com.example.api.reservation.models.ServicoExtraModel;
import com.example.api.reservation.repositories.HospedeRepository;
import com.example.api.reservation.repositories.QuartoRepository;
import com.example.api.reservation.repositories.ReservaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class ReseervaService {
    private final ReservaRepository reservaRepository;
    private final QuartoRepository quartoRepository;
    private final HospedeRepository hospedeRepository;

    public ReseervaService(ReservaRepository reservaRepository,
                           QuartoRepository quartoRepository,
                           HospedeRepository hospedeRepository) {
        this.reservaRepository = reservaRepository;
        this.quartoRepository = quartoRepository;
        this.hospedeRepository = hospedeRepository;
    }
    public ReservaModel criaReserva(QuartoModel quartoModel, HospedeModel hospedeModel, LocalDateTime checkin, LocalDateTime checkout) {
        HospedeModel hospede = hospedeRepository.findById(hospedeModel.getId())
                .orElseThrow(() -> new RuntimeException("Hospede não encontrado."));
        QuartoModel quarto = quartoRepository.findById(quartoModel.getId())
                .orElseThrow(() -> new RuntimeException("Quarto não encontrado."));
        //Regra de conflito.
        boolean existeConflito = reservaRepository.existsByQuartoAndPeriodo(quartoModel.getId(), checkin, checkout);
        if (existeConflito){
            throw new RuntimeException("Quarto já reservado para esse período.");
        }

        ReservaModel reserva = new ReservaModel();
        reserva.setHospede(hospede);
        reserva.setQuarto(quarto);
        reserva.setCheckin(checkin);
        reserva.setCheckout(checkout);
        reserva.setStatusReserva(StatusReserva.CONFIRMADA);

        return reservaRepository.save(reserva);

    }

    //cancelamento com regra de 48 horas.
    public ReservaModel cancelarReserva(ReservaModel reservaModel, LocalDateTime dataCancelamento) {
        ReservaModel reserva = reservaRepository.findById(reservaModel.getId())
                .orElseThrow(() -> new RuntimeException("Reserva não encontrada."));

        Long horasAntes = ChronoUnit.HOURS.between(dataCancelamento, reservaModel.getCheckin());

        if (horasAntes >= 48) {
            reservaModel.setStatusReserva(StatusReserva.CANCELADA);
            reservaModel.setMulta(BigDecimal.ZERO);
        } else {
            reservaModel.setStatusReserva(StatusReserva.CANCELADA);
            reservaModel.setMulta(reservaModel.getQuarto().getPrecoDiaria());
        }
        return reservaRepository.save(reserva);
    }

    public List<ReservaModel> listaReservasaPorHospede(HospedeModel hospedeModel) {
        return reservaRepository.findByHospedeId(hospedeModel.getId());
    }

    public List<ReservaModel> listaTodasAsReservas(){
        return reservaRepository.findAll();
    }
}
