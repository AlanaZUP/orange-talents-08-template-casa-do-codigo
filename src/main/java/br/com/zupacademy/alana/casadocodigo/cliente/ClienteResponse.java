package br.com.zupacademy.alana.casadocodigo.cliente;

import br.com.zupacademy.alana.casadocodigo.estado.Estado;
import br.com.zupacademy.alana.casadocodigo.paises.Pais;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ClienteResponse {

    private Long id;

    public ClienteResponse(Cliente cliente) {
        this.id = cliente.getId();
    }

    public Long getId() {
        return id;
    }

}
