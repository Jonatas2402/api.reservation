package com.example.api.reservation.models;

import com.example.api.reservation.enuns.StatusQuarto;
import com.example.api.reservation.enuns.TipoQuarto;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "quarto")
@Data
public class QuartoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String numeroQuarto;
    @Enumerated(EnumType.STRING)
    private TipoQuarto tipoQuarto;
    private Integer capacidade;
    private BigDecimal precoDiaria;
    @Enumerated(EnumType.STRING)
    private StatusQuarto statusQuarto;

    @OneToMany(mappedBy = "quarto", cascade = CascadeType.ALL)
    private List<ReservaModel> reservas = new ArrayList<>();

}
