package com.example.Helpdesk.dto;

import com.example.Helpdesk.model.ChamadosEnum.NivelSuporte;
import com.example.Helpdesk.model.ChamadosEnum.Prioridade;
import com.example.Helpdesk.model.ChamadosEnum.StatusChamado;

import java.time.LocalDateTime;

public class AtendimentoResponseDto {

    private Long id;
    private Long chamadoId;
    private String nomeTecnico;
    private String observacao;
    private Prioridade prioridade;
    private StatusChamado status;
    private NivelSuporte nivelSuporte;
    private LocalDateTime dataAtendimento;

    public AtendimentoResponseDto() {
    }

    public AtendimentoResponseDto(Long id, Long chamadoId, String nomeTecnico, String observacao,
                                  Prioridade prioridade, StatusChamado status,
                                  NivelSuporte nivelSuporte, LocalDateTime dataAtendimento) {
        this.id = id;
        this.chamadoId = chamadoId;
        this.nomeTecnico = nomeTecnico;
        this.observacao = observacao;
        this.prioridade = prioridade;
        this.status = status;
        this.nivelSuporte = nivelSuporte;
        this.dataAtendimento = dataAtendimento;
    }

    public Long getId() {
        return id;
    }

    public Long getChamadoId() {
        return chamadoId;
    }

    public String getNomeTecnico() {
        return nomeTecnico;
    }

    public String getObservacao() {
        return observacao;
    }

    public Prioridade getPrioridade() {
        return prioridade;
    }

    public StatusChamado getStatus() {
        return status;
    }

    public NivelSuporte getNivelSuporte() {
        return nivelSuporte;
    }

    public LocalDateTime getDataAtendimento() {
        return dataAtendimento;
    }
}