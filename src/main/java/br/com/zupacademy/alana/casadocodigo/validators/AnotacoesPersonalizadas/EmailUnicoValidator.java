package br.com.zupacademy.alana.casadocodigo.validators.AnotacoesPersonalizadas;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailUnicoValidator implements ConstraintValidator<EmailUnico, Object> {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Query query = manager.createQuery("select 1 from Autor a where a.email=:value");
        query.setParameter("value", value);
        if (query.getResultList().size() > 0){
            return false;
        }
        return true;
    }
}
