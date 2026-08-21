package com.example.Helpdesk.services;

import com.example.Helpdesk.dto.AtendimentoRequestDto;
import com.example.Helpdesk.dto.AtendimentoResponseDto;
import com.example.Helpdesk.model.AtendimentoModel;
import com.example.Helpdesk.model.ChamadoModel;
import com.example.Helpdesk.model.UsuarioModel;
import com.example.Helpdesk.repository.AtendimentoRepository;
import com.example.Helpdesk.repository.ChamadoRepository;
import com.example.Helpdesk.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AtendimentoService {

    private final AtendimentoRepository atendimentoRepository;
    private final ChamadoRepository chamadoRepository;
    private final UsuarioRepository usuarioRepository;

    public AtendimentoService(AtendimentoRepository atendimentoRepository,
                              ChamadoRepository chamadoRepository,
                              UsuarioRepository usuarioRepository) {
        this.atendimentoRepository = atendimentoRepository;
        this.chamadoRepository = chamadoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public AtendimentoResponseDto registrarAtendimento(AtendimentoRequestDto dto) {
        ChamadoModel chamado = chamadoRepository.findById(dto.getChamadoId())
                .orElseThrow(() -> new RuntimeException("Chamado não encontrado com o ID: " + dto.getChamadoId()));

        UsuarioModel tecnico = usuarioRepository.findById(dto.getTecnicoId())
                .orElseThrow(() -> new RuntimeException("Técnico não encontrado com o ID: " + dto.getTecnicoId()));

        // Registra o histórico de atendimento
        AtendimentoModel atendimento = new AtendimentoModel();
        atendimento.setChamado(chamado);
        atendimento.setTecnico(tecnico);
        atendimento.setObservacao(dto.getObservacao());
        atendimento.setPrioridade(dto.getPrioridade());
        atendimento.setStatus(dto.getStatus());
        atendimento.setNivelSuporte(dto.getNivelSuporte());

        AtendimentoModel salvo = atendimentoRepository.save(atendimento);

        // Atualiza os dados no chamado principal
        chamado.setPrioridade(dto.getPrioridade());
        chamado.setStatus(dto.getStatus());
        chamadoRepository.save(chamado);

        return converterParaDto(salvo);
    }

    public List<AtendimentoResponseDto> listarPorChamado(Long chamadoId) {
        return atendimentoRepository.findByChamadoId(chamadoId).stream()
                .map(this::converterParaDto)
                .collect(Collectors.toList());
    }

    private AtendimentoResponseDto converterParaDto(AtendimentoModel atendimento) {
        return new AtendimentoResponseDto(
                atendimento.getId(),
                atendimento.getChamado().getId(),
                atendimento.getTecnico().getNome(),
                atendimento.getObservacao(),
                atendimento.getPrioridade(),
                atendimento.getStatus(),
                atendimento.getNivelSuporte(),
                atendimento.getDataAtendimento()
        );
    }
}