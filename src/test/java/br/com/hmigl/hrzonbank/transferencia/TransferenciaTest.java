package br.com.hmigl.hrzonbank.transferencia;

import static org.junit.jupiter.api.Assertions.*;

import br.com.hmigl.hrzonbank.conta.Conta;
import br.com.hmigl.hrzonbank.conta.TipoConta;
import br.com.hmigl.hrzonbank.pessoa.Pessoa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;

class TransferenciaTest {
    private @PersistenceContext EntityManager manager;

    @DisplayName("Deve transferir dinheiro entre duas contas")
    @ParameterizedTest
    @CsvSource({"500,1500,0", "2.50,100,5600", "10.25,54.32,87.51"})
    void test(BigDecimal valor, BigDecimal saldoConta1, BigDecimal saldoConta2) {
        Conta conta1 = new Conta(Mockito.mock(Pessoa.class), "1234567", "8", TipoConta.CORRENTE);
        Conta conta2 = new Conta(Mockito.mock(Pessoa.class), "7654321", "0", TipoConta.POUPANCA);

        BigDecimal novoSaldoConta1 = saldoConta1.subtract(valor);
        BigDecimal novoSaldoConta2 = saldoConta2.add(valor);

        ReflectionTestUtils.setField(conta1, "saldo", saldoConta1);
        ReflectionTestUtils.setField(conta2, "saldo", saldoConta2);

        Transferencia transferencia = new Transferencia(conta1, conta2, valor);
        transferencia.processa();

        assertEquals(novoSaldoConta1, conta1.getSaldo());
        assertEquals(novoSaldoConta2, conta2.getSaldo());
    }
}
