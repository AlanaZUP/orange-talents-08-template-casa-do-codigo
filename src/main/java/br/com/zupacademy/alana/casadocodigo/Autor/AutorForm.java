package br.com.zupacademy.alana.casadocodigo.Autor;

import br.com.zupacademy.alana.casadocodigo.Validators.AnotacoesPersonalizadas.EmailUnico;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class AutorForm {
    @NotEmpty
    private String nome;

    @NotEmpty @Email @EmailUnico
    private String email;

    @NotEmpty @Length(max = 400)
    private String descricao;


    public AutorForm(@NotEmpty String nome,@NotEmpty @Email String email,@NotEmpty @Length(max = 400) String descricao) {
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
