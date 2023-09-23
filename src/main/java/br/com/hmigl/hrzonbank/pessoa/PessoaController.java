package br.com.hmigl.hrzonbank.pessoa;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("pessoas")
public class PessoaController {
    @PostMapping
    ResponseEntity<String> cadastra(@RequestBody @Valid NovaPessoaRequest request) {
        return ResponseEntity.ok("okay");
    }
}
