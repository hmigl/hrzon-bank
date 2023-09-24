package br.com.hmigl.hrzonbank.transferencia;

import static br.com.hmigl.hrzonbank.compartilhado.ValidationCondition.EXISTS;

import br.com.hmigl.hrzonbank.compartilhado.ConditionalValue;
import br.com.hmigl.hrzonbank.conta.Conta;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record NovaTransferenciaRequest(
        @NotNull @ConditionalValue(domainClass = Conta.class, fieldName = "id", condition = EXISTS)
                Long contaOrigemId,
        @NotNull @ConditionalValue(domainClass = Conta.class, fieldName = "id", condition = EXISTS)
                Long contaDestinoId,
        @NotNull @Positive @Digits(integer = Integer.MAX_VALUE, fraction = 2) BigDecimal valor) {}
