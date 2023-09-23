package br.com.hmigl.hrzonbank.conta;

import static br.com.hmigl.hrzonbank.compartilhado.ValidationCondition.EXISTS;

import br.com.hmigl.hrzonbank.compartilhado.ConditionalValue;
import br.com.hmigl.hrzonbank.pessoa.Pessoa;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record NovaContaRequest(
        @NotNull @ConditionalValue(fieldName = "id", domainClass = Pessoa.class, condition = EXISTS) Long pessoaId,
        @NotBlank String numero,
        @NotBlank @Pattern(regexp = "\\d") String digito,
        @NotNull TipoConta tipoConta) {}
