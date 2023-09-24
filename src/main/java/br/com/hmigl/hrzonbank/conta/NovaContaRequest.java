package br.com.hmigl.hrzonbank.conta;

import static br.com.hmigl.hrzonbank.compartilhado.ValidationCondition.EXISTS;

import br.com.hmigl.hrzonbank.compartilhado.ConditionalValue;
import br.com.hmigl.hrzonbank.pessoa.Pessoa;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.function.Function;

public record NovaContaRequest(
        @NotNull @ConditionalValue(fieldName = "id", domainClass = Pessoa.class, condition = EXISTS) Long pessoaId,
        @NotBlank String numero,
        @NotBlank @Pattern(regexp = "\\d") String digito,
        @NotNull TipoConta tipoConta) {
    public Conta toModel(Function<Long, Pessoa> carregaPessoa) {
        return new Conta(carregaPessoa.apply(pessoaId), numero, digito, tipoConta);
    }
}
