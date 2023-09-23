package br.com.hmigl.hrzonbank.pessoa;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("pessoas")
public class PessoaController {
    private EntityManager manager;

    public PessoaController(EntityManager manager) {
        this.manager = manager;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<String> cadastra(@RequestBody @Valid NovaPessoaRequest request) {
        Pessoa pessoa = request.toModel();
        manager.persist(pessoa);
        URI uri =
                UriComponentsBuilder.fromPath("pessoas/{id}")
                        .buildAndExpand(pessoa.getId())
                        .toUri();
        return ResponseEntity.created(uri).build();
    }
}
