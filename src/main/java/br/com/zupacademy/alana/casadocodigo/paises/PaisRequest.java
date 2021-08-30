package br.com.zupacademy.alana.casadocodigo.paises;

import br.com.zupacademy.alana.casadocodigo.validators.AnotacoesPersonalizadas.CampoUnico;

import javax.validation.constraints.NotEmpty;

public class PaisRequest {

    @NotEmpty @CampoUnico(nomeCampo = "nome", classe = Pais.class)
    private String nome;

    public String getNome() {
        return nome;
    }

    public Pais toModel(){
        return new Pais(nome);
    }
}
