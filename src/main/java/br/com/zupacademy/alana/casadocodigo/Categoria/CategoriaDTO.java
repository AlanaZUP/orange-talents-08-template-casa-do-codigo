package br.com.zupacademy.alana.casadocodigo.Categoria;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

public class CategoriaDTO {

    private Long id;
    private String nome;

    public CategoriaDTO(Categoria categoria) {
        this.nome = categoria.getNome();
        this.id = categoria.getId();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
