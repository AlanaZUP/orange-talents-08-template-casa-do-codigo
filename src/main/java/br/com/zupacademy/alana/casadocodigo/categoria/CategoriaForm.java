package br.com.zupacademy.alana.casadocodigo.categoria;

import br.com.zupacademy.alana.casadocodigo.validators.AnotacoesPersonalizadas.CampoUnico;

import javax.validation.constraints.NotEmpty;

public class CategoriaForm {

    @CampoUnico(nomeCampo = "nome", classe = Categoria.class)
    @NotEmpty
    private String nome;

//    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
//    public CategoriaForm(@NotEmpty String nome) {
//        this.nome = nome;
//    }

    public Categoria converterParaCategoria(){
        return new Categoria(this.nome);
    }

    public String getNome() {
        return nome;
    }
}
