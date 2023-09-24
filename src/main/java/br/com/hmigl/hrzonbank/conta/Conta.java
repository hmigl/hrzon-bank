package br.com.hmigl.hrzonbank.conta;

import br.com.hmigl.hrzonbank.pessoa.Pessoa;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

@Entity
public class Conta {
    private @Id @GeneratedValue Long id;

    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;

    private @NotBlank String numero;
    private @NotBlank @Pattern(regexp = "\\d") String digito;
    private @NotNull @Enumerated(EnumType.STRING) TipoConta tipoConta;
    private @PositiveOrZero BigDecimal saldo = BigDecimal.ZERO;

    @Deprecated
    public Conta() {}

    public Conta(Pessoa pessoa, String numero, String digito, TipoConta tipoConta) {
        this.pessoa = pessoa;
        this.numero = numero;
        this.digito = digito;
        this.tipoConta = tipoConta;
    }

    public void aumentaSaldo(BigDecimal quantia) {
        this.saldo = this.saldo.add(quantia);
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }
}
