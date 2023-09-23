package br.com.hmigl.hrzonbank.conta;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("contas")
public class ContaController {
    @PostMapping
    public ResponseEntity<?> cadastra(@RequestBody @Valid NovaContaRequest request) {
        return ResponseEntity.ok(request.toString());
    }
}
