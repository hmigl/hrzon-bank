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

import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.Objects;

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

    public void diminuiSaldo(BigDecimal quantia) {
        if (this.possuiSaldoSuficiente(quantia)) {
            this.saldo = this.saldo.subtract(quantia);
        }
    }

    public boolean possuiSaldoSuficiente(BigDecimal valor) {
        return this.saldo.compareTo(valor) >= 0;
    }

    public void transfere(Conta contaDestino, BigDecimal valor) {
        Assert.isTrue(
                !Objects.equals(this, contaDestino),
                "Nao e possivel transferir para a mesma conta");

        this.diminuiSaldo(valor);
        contaDestino.aumentaSaldo(valor);

        Assert.state(
                this.saldo.compareTo(BigDecimal.ZERO) > 0,
                "Ocorreu algum problema com o saldo da conta");
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Conta conta = (Conta) o;
        return Objects.equals(pessoa, conta.pessoa)
                && Objects.equals(numero, conta.numero)
                && Objects.equals(digito, conta.digito)
                && tipoConta == conta.tipoConta;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pessoa, numero, digito, tipoConta);
    }
}
