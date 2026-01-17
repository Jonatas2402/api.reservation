package com.example.api.reservation.models;

import com.example.api.reservation.enuns.StatusReserva;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "reserva")
@Data
public class ReservaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private LocalDateTime checkin;
    private LocalDateTime checkout;
    @Enumerated(EnumType.STRING)
    private StatusReserva statusReserva;
    private BigDecimal multa;

    @ManyToOne
    @JoinColumn(name = "id_hospede")
    private List<HospedeModel> hospede;

    @ManyToOne
    @JoinColumn(name = "id_quarto")
    private List<QuartoModel> quarto;

    @OneToMany(mappedBy = "reserva", cascade = CascadeType.ALL)
    private List<PagamentoModel> pagamentos = new ArrayList<>();

}
