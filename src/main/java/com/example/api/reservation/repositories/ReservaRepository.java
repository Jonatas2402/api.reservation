package com.example.api.reservation.repositories;

import com.example.api.reservation.models.ReservaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface ReservaRepository extends JpaRepository<ReservaModel, UUID> {

    List<ReservaModel> findByHospedeId(UUID id);

    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END " +
            "FROM Reserva r WHERE r.quarto.id = :quartoId " +
            "AND (r.checkIn < :checkOut AND r.checkOut > :checkIn)")
    boolean existsByQuartoAndPeriodo(@Param("quartoId") UUID quartoId,
                                     @Param("checkIn") LocalDateTime checkIn,
                                     @Param("checkOut") LocalDateTime checkOut);

}
