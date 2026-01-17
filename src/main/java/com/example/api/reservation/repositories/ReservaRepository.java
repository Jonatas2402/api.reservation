package com.example.api.reservation.repositories;

import com.example.api.reservation.models.ReservaModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReservaRepository extends JpaRepository<ReservaModel, UUID> {
}
