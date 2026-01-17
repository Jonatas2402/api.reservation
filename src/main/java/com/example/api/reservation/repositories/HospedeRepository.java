package com.example.api.reservation.repositories;

import com.example.api.reservation.models.HospedeModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface HospedeRepository extends JpaRepository<HospedeModel, UUID> {

    List<HospedeModel> findByUsuarioId(UUID id);
}
