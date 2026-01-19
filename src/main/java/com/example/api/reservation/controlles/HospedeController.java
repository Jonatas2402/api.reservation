package com.example.api.reservation.controlles;

import com.example.api.reservation.models.HospedeModel;
import com.example.api.reservation.models.UsuarioModel;
import com.example.api.reservation.services.HospedeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/hospede")
public class HospedeController {

    private final HospedeService hospedeService;

    public HospedeController(HospedeService hospedeService) {
        this.hospedeService = hospedeService;
    }

    @PostMapping("/usuario/{usuarioid}")
    public ResponseEntity<HospedeModel> criaHospede(@PathVariable("id") UsuarioModel id, @RequestBody HospedeModel hospede) {
        return ResponseEntity.ok(hospedeService.salva(id, hospede));
    }

    @GetMapping("/usuario/{usuarioid}")
    public ResponseEntity<List<HospedeModel>> listarPorUsuario(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(hospedeService.listandoHospedesPorIdDoUsuario(id));
    }
    @GetMapping
    public ResponseEntity<List<HospedeModel>> listaTodos() {
        return ResponseEntity.ok(hospedeService.listaTodosOsHospedes());
    }
    @PutMapping("/{id}")
    public ResponseEntity<HospedeModel> atualizaHospede(@PathVariable("id") HospedeModel id, @RequestBody HospedeModel hospede) {
        return ResponseEntity.ok(hospedeService.atualizaDadosHospede(id, hospede));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletaHospede(@PathVariable("id") HospedeModel hospedeId){
        hospedeService.excluiHospede(hospedeId);
        return ResponseEntity.noContent().build();
    }
}
