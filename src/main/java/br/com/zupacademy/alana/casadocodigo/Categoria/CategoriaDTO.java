package br.com.zupacademy.alana.casadocodigo.Categoria;

import br.com.zupacademy.alana.casadocodigo.Validators.AnotacoesPersonalizadas.ValorUnico;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

public class Categoria {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    private String nome;

    public Categoria(@NotEmpty String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

}
