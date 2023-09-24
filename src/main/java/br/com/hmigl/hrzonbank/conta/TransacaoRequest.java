package br.com.hmigl.hrzonbank.conta;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record TransacaoRequest(
        @Positive @Digits(integer = Integer.MAX_VALUE, fraction = 2) BigDecimal quantia) {}
