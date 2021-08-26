package br.com.zupacademy.alana.casadocodigo.Validators.AnotacoesPersonalizadas;

import br.com.zupacademy.alana.casadocodigo.Autor.Autor;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class EmailUnicoValidator implements ConstraintValidator<EmailUnicoValid, Object> {

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
