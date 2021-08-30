package br.com.zupacademy.alana.casadocodigo.estado;

import br.com.zupacademy.alana.casadocodigo.estadoTeste.Estado;
import br.com.zupacademy.alana.casadocodigo.paises.Pais;

public class EstadoResponse {
    private Long id;
    private Pais pais;

    public EstadoResponse(Estado estado) {
        this.id = estado.getId();
        this.pais = estado.getPais();
    }

    public Long getId() {
        return id;
    }

    public Pais getPais() {
        return pais;
    }
}
