package br.com.hmigl.hrzonbank.conta;

import static org.junit.jupiter.api.Assertions.*;

import br.com.hmigl.hrzonbank.compartilhado.CustomMockMvc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Map;

@SpringBootTest
@AutoConfigureMockMvc
class ContaControllerTest {
    private static final String URI = "/contas";

    private @Autowired CustomMockMvc mockMvc;

    @DisplayName("Deve cadastrar duas contas de tipos diferentes para uma pessoa")
    @ParameterizedTest
    @CsvSource({"1,09557205,4,CORRENTE", "1,1043815,7,POUPANCA"})
    @DirtiesContext
    void test(Long pessoaId, String numero, String digito, TipoConta tipoConta) throws Exception {
        mockMvc.post(
                "/pessoas",
                Map.of("nome", "fulano", "telefone", "94996457774", "cpf", "20487101154"));

        mockMvc.post(
                        URI,
                        Map.of(
                                "pessoaId",
                                pessoaId,
                                "numero",
                                numero,
                                "digito",
                                digito,
                                "tipoConta",
                                tipoConta))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @DisplayName("Deve falhar ao cadastrar duas contas de mesmo tipo para uma pessoa")
    @ParameterizedTest
    @CsvSource({"1,09557205,4,CORRENTE,201", "1,1043815,7,CORRENTE,400"})
    void test2(Long pessoaId, String numero, String digito, TipoConta tipoConta, int status)
            throws Exception {
        mockMvc.post(
                "/pessoas",
                Map.of("nome", "fulano", "telefone", "94996457774", "cpf", "77040052601"));

        mockMvc.post(
                        URI,
                        Map.of(
                                "pessoaId",
                                pessoaId,
                                "numero",
                                numero,
                                "digito",
                                digito,
                                "tipoConta",
                                tipoConta))
                .andExpect(MockMvcResultMatchers.status().is(status));
    }
}
