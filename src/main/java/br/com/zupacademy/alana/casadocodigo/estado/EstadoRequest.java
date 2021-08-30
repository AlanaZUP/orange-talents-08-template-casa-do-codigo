package br.com.zupacademy.alana.casadocodigo.estado;

import br.com.zupacademy.alana.casadocodigo.paises.Pais;
import br.com.zupacademy.alana.casadocodigo.paises.PaisRepository;
import br.com.zupacademy.alana.casadocodigo.validators.AnotacoesPersonalizadas.ExistisId;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class EstadoRequest {

    @NotEmpty
    private String nome;

    @NotNull
    @ExistisId(classe = Pais.class)
    private Long idPais;

    public String getNome() {
        return nome;
    }

    public Long getIdPais() {
        return idPais;
    }

    public Estado toModel(PaisRepository paisRepository){
        Pais pais = paisRepository.findById(idPais).get();
        return new Estado(nome, pais);
    }
}