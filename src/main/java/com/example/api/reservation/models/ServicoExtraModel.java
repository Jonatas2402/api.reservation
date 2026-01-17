package com.example.api.reservation.models;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "servicos")
@Data
public class ServicoExtraModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String descricao;
    private BigDecimal valor;

    @ManyToOne
    @JoinColumn(name = "id_reserva")
    private ReservaModel reserva;
}
