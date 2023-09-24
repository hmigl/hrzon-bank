package br.com.hmigl.hrzonbank.transferencia;

import br.com.hmigl.hrzonbank.conta.Conta;

import jakarta.persistence.EntityManager;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Objects;

@Component
public class TransferenciaValidaValidator implements Validator {
    private EntityManager manager;

    public TransferenciaValidaValidator(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return NovaTransferenciaRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }

        NovaTransferenciaRequest request = (NovaTransferenciaRequest) target;
        Conta contaOrigem = manager.find(Conta.class, request.contaOrigemId());
        if (Objects.equals(request.contaOrigemId(), request.contaDestinoId())) {
            errors.rejectValue(
                    "contaDestinoId",
                    null,
                    "Uma transferencia nao pode ser realizada para a mesma conta");
        }
        if (!contaOrigem.possuiSaldoSuficiente(request.valor())) {
            errors.rejectValue("valor", null, "Conta de origem nao possui saldo suficiente");
        }
    }
}
