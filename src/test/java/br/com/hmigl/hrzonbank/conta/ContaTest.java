package br.com.hmigl.hrzonbank.conta;

import static org.junit.jupiter.api.Assertions.assertEquals;

import br.com.hmigl.hrzonbank.pessoa.Pessoa;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;

class ContaTest {

    @DisplayName("Deve aumentar saldo da conta")
    @ParameterizedTest
    @CsvSource({"50.00,10.50,60.50", "1,500,501", "619.21,561.42,1180.63"})
    void test(BigDecimal quantiaInical, BigDecimal soma, BigDecimal quantiaFinal) {
        Conta conta = new Conta(Mockito.mock(Pessoa.class), "641841", "5", TipoConta.CORRENTE);
        ReflectionTestUtils.setField(conta, "saldo", quantiaInical);
        conta.aumentaSaldo(soma);
        assertEquals(0, conta.getSaldo().compareTo(quantiaFinal));
    }

    @DisplayName("Deve diminuir saldo da conta")
    @ParameterizedTest
    @CsvSource({"50.00,10.50,39.5", "1.42,51951.51,1.42", "619.21,561.42,57.79"})
    void test2(BigDecimal quantiaInical, BigDecimal subtraendo, BigDecimal quantiaFinal) {
        Conta conta = new Conta(Mockito.mock(Pessoa.class), "641841", "5", TipoConta.POUPANCA);
        ReflectionTestUtils.setField(conta, "saldo", quantiaInical);
        conta.diminuiSaldo(subtraendo);
        assertEquals(0, conta.getSaldo().compareTo(quantiaFinal));
    }
}
