package br.com.zupacademy.alana.casadocodigo.Categoria;

import br.com.zupacademy.alana.casadocodigo.Validators.AnotacoesPersonalizadas.CampoUnico;

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
