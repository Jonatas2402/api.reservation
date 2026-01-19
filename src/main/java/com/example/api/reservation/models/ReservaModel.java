package com.example.api.reservation.models;

import com.example.api.reservation.enuns.StatusReserva;
import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime checkin;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime checkout;
    @Enumerated(EnumType.STRING)
    private StatusReserva statusReserva;
    private BigDecimal multa;

    @ManyToOne
    @JoinColumn(name = "id_hospede")
    private HospedeModel hospede;

    @ManyToOne
    @JoinColumn(name = "id_quarto")
    private QuartoModel quarto;

    @OneToMany(mappedBy = "reserva", cascade = CascadeType.ALL)
    private List<PagamentoModel> pagamentos = new ArrayList<>();

}
