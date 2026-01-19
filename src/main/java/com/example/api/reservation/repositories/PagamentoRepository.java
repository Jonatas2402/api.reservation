package com.example.api.reservation.repositories;

import com.example.api.reservation.models.PagamentoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PagamentoRepository extends JpaRepository<PagamentoModel, UUID> {

}
