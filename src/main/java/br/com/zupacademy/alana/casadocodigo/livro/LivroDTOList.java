package br.com.zupacademy.alana.casadocodigo.livro;

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
