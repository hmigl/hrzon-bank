package br.com.hmigl.hrzonbank.pessoa;

import static br.com.hmigl.hrzonbank.compartilhado.ValidationCondition.UNIQUE;

import br.com.hmigl.hrzonbank.compartilhado.ConditionalValue;

import jakarta.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.br.CPF;

public record NovaPessoaRequest(
        @NotBlank String nome,
        @NotBlank String telefone,
        @NotBlank @CPF @ConditionalValue(domainClass = Pessoa.class, fieldName = "cpf", condition = UNIQUE) String cpf) {
    public Pessoa toModel() {
        return new Pessoa(nome, telefone, cpf);
    }
}
