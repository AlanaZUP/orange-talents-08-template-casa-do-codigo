package br.com.zupacademy.alana.casadocodigo.livro;

import br.com.zupacademy.alana.casadocodigo.autor.Autor;
import br.com.zupacademy.alana.casadocodigo.categoria.Categoria;
import com.fasterxml.jackson.annotation.JsonFormat;
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

    @NotNull @Future @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataPublicacao;

    @NotNull @ManyToOne
    private Categoria categoria;

    @NotNull @ManyToOne
    private Autor autor;

    @Deprecated
    public Livro() {
    }

    /**
     * @param titulo o titulo do livro deve ser uma String
     * @param resumo o resumo do livro deve ser uma String de até 500 caracteres
     * @param sumario o sumario do livro
     * @param preco o preco do livro, deve ser acima de 20.0
     * @param numeroPaginas o número de páginas do livro deve ser acima de 100
     * @param resumo o resumo do livro deve ser uma String de até 500 caracteres
     * @param isbn o isbn é o identificador do livro
     * @param dataPublicacao a data de publicação do livro deve ser futura
     * @param autor representa o autor do livro
     * @param categoria representa a categoria que o livro pertence
     * */


    public Livro(@NotEmpty String titulo, @NotEmpty @Length(max = 500) String resumo, @NotEmpty String sumario, @NotNull @DecimalMin(value = "20.0") BigDecimal preco, @NotNull @Min(100) int numeroPaginas, @NotEmpty String isbn,@NotNull @Future @JsonFormat(pattern = "yyyy-MM-dd") LocalDate dataPublicacao, @NotNull @Valid Categoria categoria, @NotNull @Valid Autor autor) {
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
