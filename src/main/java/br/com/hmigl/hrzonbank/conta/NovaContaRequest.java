package br.com.hmigl.hrzonbank.conta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record NovaContaRequest(
        @NotNull Long pessoaId,
        @NotBlank String numero,
        @NotBlank @Pattern(regexp = "\\d") String digito,
        @NotNull TipoConta tipoConta) {}
