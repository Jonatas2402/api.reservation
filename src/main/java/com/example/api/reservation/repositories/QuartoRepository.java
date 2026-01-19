package com.example.api.reservation.repositories;

import com.example.api.reservation.enuns.StatusQuarto;
import com.example.api.reservation.models.QuartoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface QuartoRepository extends JpaRepository<QuartoModel, UUID> {
    boolean existsByNumeroQuarto (String numeroQuarto);

    boolean existsByQuartoStatus(StatusQuarto statusQuarto);
}
