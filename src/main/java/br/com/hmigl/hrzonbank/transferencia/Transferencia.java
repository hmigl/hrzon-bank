package br.com.hmigl.hrzonbank.transferencia;

import br.com.hmigl.hrzonbank.conta.Conta;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;

import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Transferencia {
    private @Id @GeneratedValue Long id;

    @ManyToOne
    @JoinColumn(name = "conta_origem_id")
    private Conta contaOrigem;

    @ManyToOne
    @JoinColumn(name = "conta_destino_id")
    private Conta contaDestino;

    private @NotNull @Positive @Digits(integer = 13, fraction = 2) BigDecimal valor =
            BigDecimal.ZERO;
    private @NotNull @PastOrPresent LocalDate data;

    @Deprecated
    public Transferencia() {}

    public Transferencia(Conta contaOrigem, Conta contaDestino, BigDecimal valor) {
        this.contaOrigem = contaOrigem;
        this.contaDestino = contaDestino;
        this.valor = valor;
        this.data = LocalDate.now();
    }

    public void processa() {
        Assert.state(
                Objects.nonNull(this.contaOrigem),
                "Nao e possivel usar uma conta inexistente para realizar transferencia");
        Assert.state(
                Objects.nonNull(this.contaDestino),
                "Nao e possivel transferir para uma conta inexistente");
        Assert.state(
                Objects.nonNull(this.valor) && this.valor.compareTo(BigDecimal.ZERO) > 0,
                "Valor selecionado deve ser maior que 0");
        this.contaOrigem.transferePara(this.contaDestino, this.valor);
    }

    public Long getId() {
        return id;
    }
}
