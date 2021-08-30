package br.com.zupacademy.alana.casadocodigo.categoria;

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
