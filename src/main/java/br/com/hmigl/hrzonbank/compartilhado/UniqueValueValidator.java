package br.com.hmigl.hrzonbank.compartilhado;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class UniqueValueValidator implements ConstraintValidator<UniqueValue, Object> {
    private EntityManager manager;
    private String fieldName;
    private Class<?> domainClass;

    public UniqueValueValidator(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public void initialize(UniqueValue constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        this.fieldName = constraintAnnotation.fieldName();
        this.domainClass = constraintAnnotation.domainClass();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        Query query =
                manager.createQuery(
                        "SELECT 1 FROM " + domainClass.getName() + " WHERE " + fieldName + " =:o");
        query.setParameter("o", o);
        List<?> res = query.getResultList();

        return res.isEmpty();
    }
}
