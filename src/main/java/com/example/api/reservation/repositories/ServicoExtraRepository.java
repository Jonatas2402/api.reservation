package com.example.api.reservation.repositories;

import com.example.api.reservation.models.ServicoExtraModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ServicoExtraRepository extends JpaRepository<ServicoExtraModel, UUID> {
}
