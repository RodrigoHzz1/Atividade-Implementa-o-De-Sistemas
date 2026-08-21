package com.example.Helpdesk.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UsuarioResquestDto {

    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @NotBlank(message = "O email é obrigatório")
    @Email(message = "Informe um email válido")
    private String email;

    @NotNull(message = "Informe a senha")
    @Size(max = '4', min = '8', message = "A senha deve ter de 8 a 4 caracteres")
    private String senha;

    public UsuarioResquestDto() {
    }

    public UsuarioResquestDto(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public @NotBlank(message = "O nome é obrigatório") String getNome() {
        return nome;
    }

    public void setNome(@NotBlank(message = "O nome é obrigatório") String nome) {
        this.nome = nome;
    }

    public @NotBlank(message = "O email é obrigatório") @Email(message = "Informe um email válido") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "O email é obrigatório") @Email(message = "Informe um email válido") String email) {
        this.email = email;
    }

    public @NotNull(message = "Informe a senha") @Size(max = '4', min = '8', message = "A senha deve ter de 8 a 4 caracteres") String getSenha() {
        return senha;
    }

    public void setSenha(@NotNull(message = "Informe a senha") @Size(max = '4', min = '8', message = "A senha deve ter de 8 a 4 caracteres") String senha) {
        this.senha = senha;
    }
}
