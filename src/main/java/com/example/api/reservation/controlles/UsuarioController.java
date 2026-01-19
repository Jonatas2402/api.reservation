package com.example.api.reservation.controlles;

import com.example.api.reservation.models.UsuarioModel;
import com.example.api.reservation.services.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<UsuarioModel> criarUsuario(@RequestBody UsuarioModel usuario) {
        return ResponseEntity.ok(usuarioService.criandoUsuario(usuario));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioModel> buscaPorId(@PathVariable("id")UUID id) {
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorId(id));
    }
    @GetMapping
    public ResponseEntity<List<UsuarioModel>> listarTodos(){
        return ResponseEntity.ok(usuarioService.listarTodosOsUsuarios());
    }
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioModel> atualizarPorId(@PathVariable("id") UUID id ,@RequestBody UsuarioModel usuario){
        return ResponseEntity.ok(usuarioService.atualizarUsuario(usuario));
    }

}
