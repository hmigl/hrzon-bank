package br.com.hmigl.hrzonbank.pessoa;

import jakarta.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.br.CPF;

public record NovaPessoaRequest(
        @NotBlank String nome, @NotBlank String telefone, @NotBlank @CPF String cpf) {}
