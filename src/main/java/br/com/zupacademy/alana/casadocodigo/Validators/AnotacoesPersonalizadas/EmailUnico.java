package br.com.zupacademy.alana.casadocodigo.Validators.AnotacoesPersonalizadas;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {EmailUnicoValidator.class})
public @interface EmailUnico {
    String message() default "Email já cadastrado";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
