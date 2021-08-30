package br.com.zupacademy.alana.casadocodigo.Livro;

import br.com.zupacademy.alana.casadocodigo.Autor.Autor;
import br.com.zupacademy.alana.casadocodigo.Categoria.Categoria;

import java.math.BigDecimal;
import java.time.LocalDate;

public class LivroDTOList {

    private Long id;
    private String titulo;

    public LivroDTOList(Livro livro) {
        this.id = livro.getId();
        this.titulo = livro.getTitulo();
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }
}
