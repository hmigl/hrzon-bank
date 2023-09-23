package br.com.hmigl.hrzonbank.pessoa;

import br.com.hmigl.hrzonbank.compartilhado.UniqueValue;

import jakarta.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.br.CPF;

public record NovaPessoaRequest(
        @NotBlank String nome,
        @NotBlank String telefone,
        @NotBlank @CPF @UniqueValue(domainClass = Pessoa.class, fieldName = "cpf") String cpf) {
    public Pessoa toModel() {
        return new Pessoa(nome, telefone, cpf);
    }
}
