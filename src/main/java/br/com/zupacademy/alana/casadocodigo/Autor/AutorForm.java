package br.com.zupacademy.alana.casadocodigo.Autor;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

public class AutorForm {
    @NotEmpty
    private String nome;

    @NotEmpty @Email
    private String email;

    @NotEmpty @Length(max = 400)
    private String descricao;


    public AutorForm(String nome, String email, String descricao) {
        this.nome = nome;
        this.email = email;
        this.descricao = descricao;
    }

    public Autor converterParaAutor(){
        return new Autor(this.nome, this.email, this.descricao);
    }

    @Override
    public String toString() {
        return "AutorForm{" +
                "nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
