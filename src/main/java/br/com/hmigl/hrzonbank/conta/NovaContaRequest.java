package br.com.hmigl.hrzonbank.conta;

import br.com.hmigl.hrzonbank.compartilhado.IdExists;
import br.com.hmigl.hrzonbank.pessoa.Pessoa;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record NovaContaRequest(
        @NotNull @IdExists(fieldName = "id", domainClass = Pessoa.class) Long pessoaId,
        @NotBlank String numero,
        @NotBlank @Pattern(regexp = "\\d") String digito,
        @NotNull TipoConta tipoConta) {}
