package br.com.hmigl.hrzonbank.pessoa;

import static org.junit.jupiter.api.Assertions.*;

import br.com.hmigl.hrzonbank.compartilhado.CustomMockMvc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Map;

@SpringBootTest
@AutoConfigureMockMvc
class PessoaControllerTest {
    private @Autowired CustomMockMvc mockMvc;

    private static final String URI = "/pessoas";

    @DisplayName("Deve cadastrar uma nova pessoa e falhar ao cadastra-la novamente")
    @ParameterizedTest
    @CsvSource({
        "fulano,33731142767,96638826700",
        "ciclano,17369361403,27420927146",
        "beltrano,94735443916,85686996180"
    })
    void test(String nome, String telefone, String cpf) throws Exception {
        mockMvc.post(URI, Map.of("nome", nome, "telefone", telefone, "cpf", cpf))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
        mockMvc.post(URI, Map.of("nome", nome, "telefone", telefone, "cpf", cpf))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }
}
