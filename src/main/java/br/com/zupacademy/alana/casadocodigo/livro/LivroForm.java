package br.com.zupacademy.alana.casadocodigo.livro;

import br.com.zupacademy.alana.casadocodigo.autor.Autor;
import br.com.zupacademy.alana.casadocodigo.categoria.Categoria;
import br.com.zupacademy.alana.casadocodigo.validators.AnotacoesPersonalizadas.CampoUnico;
import br.com.zupacademy.alana.casadocodigo.validators.AnotacoesPersonalizadas.ExistisId;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class LivroForm {
    @NotEmpty @CampoUnico(nomeCampo = "titulo", classe = Livro.class)
    private String titulo;

    @NotEmpty @Length(max = 500)
    private String resumo;

    @NotEmpty
    private String sumario;

    @NotNull @DecimalMin(value = "20.0")
    private BigDecimal preco;

    @NotNull @Min(100)
    private int numeroPaginas;

    @NotEmpty @CampoUnico(nomeCampo = "isbn", classe = Livro.class)
    private String isbn;

    @NotNull @Future @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataPublicacao;

    @NotNull @ExistisId(classe = Autor.class)
    private Long idAutor;

    @NotNull @ExistisId(classe = Categoria.class)
    private Long idCategoria;

    /**
     * @param titulo o titulo do livro deve ser uma String
     * @param resumo o resumo do livro deve ser uma String de até 500 caracteres
     * @param sumario o sumario do livro
     * @param preco o preco do livro, deve ser acima de 20.0
     * @param numeroPaginas o número de páginas do livro deve ser acima de 100
     * @param resumo o resumo do livro deve ser uma String de até 500 caracteres
     * @param isbn o isbn é o identificador do livro
     * @param dataPublicacao a data de publicação do livro deve ser futura
     * @param idAutor representa a identificação do autor do livro
     * @param idCategoria representa a identificação da categoria que o livro pertence
     * */
    public LivroForm(@NotEmpty String titulo, @NotEmpty @Length(max = 500) String resumo, @NotEmpty String sumario, @NotNull @DecimalMin(value = "20.0") BigDecimal preco, @NotNull @Min(100) int numeroPaginas, @NotEmpty String isbn,@NotNull @Future @JsonFormat(pattern = "yyyy-MM-dd") LocalDate dataPublicacao, @NotNull Long idCategoria, @NotNull Long idAutor) {
        this.titulo = titulo;
        this.resumo = resumo;
        this.sumario = sumario;
        this.preco = preco;
        this.numeroPaginas = numeroPaginas;
        this.isbn = isbn;
        this.dataPublicacao = dataPublicacao;
        this.idAutor = idAutor;
        this.idCategoria = idCategoria;
    }

    public Livro converterParaLivro(EntityManager manager){
        Autor autor = manager.find(Autor.class, idAutor);
        Categoria categoria = manager.find(Categoria.class, idCategoria);
        return new Livro(titulo, resumo, sumario, preco, numeroPaginas, isbn, dataPublicacao, categoria, autor);
    }

}
