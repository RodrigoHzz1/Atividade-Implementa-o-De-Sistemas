package com.example.Helpdesk.services;

import com.example.Helpdesk.dto.ChamadoRequestDto;
import com.example.Helpdesk.dto.ChamadoResponseDto;
import com.example.Helpdesk.model.ChamadoModel;
import com.example.Helpdesk.model.UsuarioModel;
import com.example.Helpdesk.repository.ChamadoRepository;
import com.example.Helpdesk.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChamadoService {

    private final ChamadoRepository chamadoRepository;
    private final UsuarioRepository usuarioRepository;

    public ChamadoService(ChamadoRepository chamadoRepository, UsuarioRepository usuarioRepository) {
        this.chamadoRepository = chamadoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public ChamadoResponseDto criar(ChamadoRequestDto dto) {
        UsuarioModel solicitante = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário solicitante não encontrado."));

        ChamadoModel chamado = new ChamadoModel();
        chamado.setTitulo(dto.getTitulo());
        chamado.setDescricao(dto.getDescricao());
        chamado.setEquipamento(dto.getEquipamento());
        chamado.setSolicitante(solicitante);

        ChamadoModel salvo = chamadoRepository.save(chamado);
        return converterParaDto(salvo);
    }

    public List<ChamadoResponseDto> listarTodos() {
        return chamadoRepository.findAll().stream()
                .map(this::converterParaDto)
                .collect(Collectors.toList());
    }

    private ChamadoResponseDto converterParaDto(ChamadoModel chamado) {
        return new ChamadoResponseDto(
                chamado.getId(),
                chamado.getTitulo(),
                chamado.getDescricao(),
                chamado.getEquipamento(),
                chamado.getPrioridade(),
                chamado.getStatus(),
                chamado.getSolicitante().getNome(),
                chamado.getDataCriacao()
        );
    }
}