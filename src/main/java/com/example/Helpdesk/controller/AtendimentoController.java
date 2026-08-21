package com.example.Helpdesk.controller;

import com.example.Helpdesk.dto.AtendimentoRequestDto;
import com.example.Helpdesk.dto.AtendimentoResponseDto;
import com.example.Helpdesk.services.AtendimentoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/atendimentos")
public class AtendimentoController {

    private final AtendimentoService atendimentoService;

    public AtendimentoController(AtendimentoService atendimentoService) {
        this.atendimentoService = atendimentoService;
    }

    @PostMapping
    public ResponseEntity<AtendimentoResponseDto> registrar(@Valid @RequestBody AtendimentoRequestDto dto) {
        AtendimentoResponseDto resposta = atendimentoService.registrarAtendimento(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
    }

    @GetMapping("/chamado/{chamadoId}")
    public ResponseEntity<List<AtendimentoResponseDto>> listarPorChamado(@PathVariable Long chamadoId) {
        return ResponseEntity.ok(atendimentoService.listarPorChamado(chamadoId));
    }
}