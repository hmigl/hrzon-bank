package br.com.hmigl.hrzonbank.transferencia;

import br.com.hmigl.hrzonbank.conta.Conta;

import jakarta.persistence.EntityManager;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

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
        Transferencia transferencia = request.toModel(id -> manager.find(Conta.class, id));
        transferencia.transfere();
        URI uri =
                UriComponentsBuilder.fromPath("transferencias/{id}")
                        .buildAndExpand(transferencia.getId())
                        .toUri();
        return ResponseEntity.created(uri).build();
    }
}
