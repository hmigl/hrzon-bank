package br.com.hmigl.hrzonbank.config;

import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjectConfig {
    @Bean
    public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory>
            webServerFactoryCustomizer() {
        return factory -> factory.setContextPath("/api/v1");
    }
}
