package com.example.api.reservation.models;

import com.example.api.reservation.enuns.Metodo;
import com.example.api.reservation.enuns.StatusPg;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "pagamento")
@Data
public class PagamentoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Enumerated(EnumType.STRING)
    private Metodo metodo;
    @Enumerated(EnumType.STRING)
    private StatusPg statusPg;

    @OneToOne
    @JoinColumn(name = "id_reserva")
    private ReservaModel reserva;

}
