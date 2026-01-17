package com.example.api.reservation.repositories;

import com.example.api.reservation.models.ReservaModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface ReservaRepository extends JpaRepository<ReservaModel, UUID> {

    List<ReservaModel> findByHospedeId(UUID id);

    boolean existsByQuartoAndCheckinAndCheckout(UUID id, LocalDateTime checkin, LocalDateTime checkout);

}
