package br.com.hmigl.hrzonbank.conta;

import br.com.hmigl.hrzonbank.pessoa.Pessoa;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
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
@RequestMapping("contas")
public class ContaController {

    private EntityManager manager;

    private MaximoUmaContaDeCadaTipoValidator validator;

    public ContaController(EntityManager manager, MaximoUmaContaDeCadaTipoValidator validator) {
        this.manager = manager;
        this.validator = validator;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(validator);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastra(@RequestBody @Valid NovaContaRequest request) {
        Conta conta = request.toModel(id -> manager.find(Pessoa.class, id));
        manager.persist(conta);
        URI uri =
                UriComponentsBuilder.fromPath("contas/{id}").buildAndExpand(conta.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
