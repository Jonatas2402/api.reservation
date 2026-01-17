package com.example.api.reservation.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Entity
@Table(name = "hospede")
@Data
public class HospedeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String nome;
    private Integer cpfPassaporte;
    private String email;
    private Integer telefone;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private UsuarioModel usuario;

    @OneToMany(mappedBy = "hospede", cascade = CascadeType.ALL)
    private List<ReservaModel> reservas = new ArrayList<>();

}
