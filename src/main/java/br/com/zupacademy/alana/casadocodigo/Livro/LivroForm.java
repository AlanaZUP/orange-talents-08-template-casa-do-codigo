package br.com.zupacademy.alana.casadocodigo.Livro;

import br.com.zupacademy.alana.casadocodigo.Autor.Autor;
import br.com.zupacademy.alana.casadocodigo.Categoria.Categoria;
import br.com.zupacademy.alana.casadocodigo.Validators.AnotacoesPersonalizadas.CampoUnico;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Livro {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String titulo;

    @NotEmpty @Length(max = 500)
    private String resumo;

    @NotEmpty
    private String sumario;

    @NotNull @DecimalMin(value = "20.0")
    private BigDecimal preco;

    @NotNull @Min(100)
    private int numeroPaginas;

    @NotEmpty
    private String isbn;

    @Future
    private LocalDate dataPublicacao;

    @NotNull @ManyToOne
    private Categoria categoria;

    @NotNull @ManyToOne
    private Autor autor;

    public Livro(@NotEmpty String titulo, @NotEmpty @Length(max = 500) String resumo, @NotEmpty String sumario, @NotNull @DecimalMin(value = "20.0") BigDecimal preco, @NotNull @Min(100) int numeroPaginas, @NotEmpty String isbn, @Future LocalDate dataPublicacao, @NotNull @Valid Categoria categoria, @NotNull @Valid Autor autor) {
        this.titulo = titulo;
        this.resumo = resumo;
        this.sumario = sumario;
        this.preco = preco;
        this.numeroPaginas = numeroPaginas;
        this.isbn = isbn;
        this.dataPublicacao = dataPublicacao;
        this.categoria = categoria;
        this.autor = autor;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getResumo() {
        return resumo;
    }

    public String getSumario() {
        return sumario;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public int getNumeroPaginas() {
        return numeroPaginas;
    }

    public String getIsbn() {
        return isbn;
    }

    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Autor getAutor() {
        return autor;
    }
}
