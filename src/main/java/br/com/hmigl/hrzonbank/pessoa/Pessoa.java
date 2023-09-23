package br.com.hmigl.hrzonbank.pessoa;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.br.CPF;

@Entity
public class Pessoa {
    private @Id @GeneratedValue Long id;

    private @NotBlank String nome;
    private @NotBlank String telefone;
    private @NotBlank @CPF String cpf;

    @Deprecated
    public Pessoa() {}

    public Pessoa(String nome, String telefone, String cpf) {
        this.nome = nome;
        this.telefone = telefone;
        this.cpf = cpf;
    }

    public Long getId() {
        return id;
    }
}
