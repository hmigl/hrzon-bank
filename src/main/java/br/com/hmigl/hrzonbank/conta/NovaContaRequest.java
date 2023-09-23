package br.com.hmigl.hrzonbank.conta;

public record NovaContaRequest(
        Long pessoaId, String numero, Character digito, TipoConta tipoConta) {}
