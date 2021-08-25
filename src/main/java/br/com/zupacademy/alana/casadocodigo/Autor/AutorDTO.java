package br.com.zupacademy.alana.casadocodigo.Autor;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

public class AutorDTO {
    private Long id;

    private String nome;

    private String email;

    private String descricao;

    private LocalDateTime instanteCadastro;

    public AutorDTO(Autor autor) {
        this.id = autor.getId();
        this.nome = autor.getNome();
        this.email = autor.getEmail();
        this.descricao = autor.getDescricao();
        this.instanteCadastro = autor.getInstanteCadastro();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDateTime getInstanteCadastro() {
        return instanteCadastro;
    }
}
