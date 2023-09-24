package br.com.hmigl.hrzonbank.conta;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class MaximoUmaContaDeCadaTipoValidator implements Validator {
    private EntityManager manager;

    public MaximoUmaContaDeCadaTipoValidator(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return NovaContaRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }
        NovaContaRequest request = (NovaContaRequest) target;

        Query query =
                manager.createQuery(
                        "SELECT COUNT(c) FROM Conta c WHERE c.pessoa.id = :pessoaId AND c.tipoConta = :tipoConta",
                        Long.class);
        query.setParameter("pessoaId", request.pessoaId());
        query.setParameter("tipoConta", request.tipoConta());

        Long res = (Long) query.getSingleResult();
        if (res >= 1) {
            errors.rejectValue("tipoConta", null, "Usuario ja possui uma conta deste tipo");
        }
    }
}
