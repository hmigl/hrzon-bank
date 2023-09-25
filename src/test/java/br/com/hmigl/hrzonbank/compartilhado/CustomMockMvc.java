package br.com.hmigl.hrzonbank.compartilhado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Map;

@Component
public class CustomMockMvc {
    private @Autowired MockMvc mockMvc;
    private @Autowired Jackson2ObjectMapperBuilder mapperBuilder;

    public ResultActions post(String uri, Map<String, Object> params) {
        try {
            String payload = this.mapperBuilder.build().writeValueAsString(params);
            MockHttpServletRequestBuilder content =
                    MockMvcRequestBuilders.post(uri)
                            .content(payload)
                            .contentType(MediaType.APPLICATION_JSON);

            return this.mockMvc.perform(content);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
