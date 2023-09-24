package br.com.hmigl.hrzonbank.transferencia;

import jakarta.persistence.EntityManager;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("transferencias")
public class TransferenciaController {
    private EntityManager manager;
    private TransferenciaValidaValidator validator;

    public TransferenciaController(EntityManager manager, TransferenciaValidaValidator validator) {
        this.manager = manager;
        this.validator = validator;
    }

    @InitBinder("novaTransferenciaRequest")
    void initBinder(WebDataBinder binder) {
        binder.addValidators(validator);
    }

    @PostMapping
    public ResponseEntity<?> processa(@RequestBody @Valid NovaTransferenciaRequest request) {
        return ResponseEntity.ok(request.toString());
    }
}
