package br.com.zupacademy.alana.casadocodigo.cliente;

import br.com.zupacademy.alana.casadocodigo.estado.Estado;
import br.com.zupacademy.alana.casadocodigo.estado.EstadoRepository;
import br.com.zupacademy.alana.casadocodigo.paises.Pais;
import br.com.zupacademy.alana.casadocodigo.paises.PaisRepository;
import br.com.zupacademy.alana.casadocodigo.validators.AnotacoesPersonalizadas.CampoUnico;
import br.com.zupacademy.alana.casadocodigo.validators.AnotacoesPersonalizadas.ExistisId;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.util.Assert;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Optional;

public class ClienteRequest {

    @Email
    @NotEmpty
    @CampoUnico(classe = Cliente.class, nomeCampo = "email")
    private String email;

    @NotEmpty
    private String nome;

    @NotEmpty
    private String sobrenome;

    @NotEmpty
    private String complemento;

    @NotEmpty
    private String endereco;

    @CPF @CampoUnico(classe = Cliente.class, nomeCampo = "documento")
    private String cpf;

    @CNPJ @CampoUnico(classe = Cliente.class, nomeCampo = "documento")
    private String cnpj;

    @NotEmpty
    private String cidade;

    @ExistisId(classe = Pais.class)
    @NotNull
    private Long idPais;

    private Long idEstado;

    @NotEmpty
    private String telefone;

    @NotNull
    private String cep;

    public Cliente toModel(PaisRepository paisRepository, EstadoRepository estadoRepository){
        Pais pais = paisRepository.findById(idPais).get();
        Optional<Estado> estadoOptional = estadoRepository.findById(idEstado);
        Estado estado = estadoOptional.get();
        String documento = "";
        if(cnpj == null || cnpj.isEmpty()) documento = cnpj;
        if(cnpj == null || cnpj.isEmpty()) documento = cpf;

        return new Cliente(email, nome, sobrenome, documento, endereco, complemento, cidade, pais, estado, telefone, cep);
    }


    public Cliente toModel(PaisRepository paisRepository){
        Pais pais = paisRepository.findById(idPais).get();
        String documento = "";
        if(cnpj == null || cnpj.isEmpty()) documento = cnpj;
        if(cnpj == null || cnpj.isEmpty()) documento = cpf;

        return new Cliente(email, nome, sobrenome, documento, endereco, complemento, cidade, pais, telefone, cep);
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getCpf() {
        return cpf;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getCidade() {
        return cidade;
    }

    public Long getIdPais() {
        return idPais;
    }

    public Long getIdEstado() {
        return idEstado;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCep() {
        return cep;
    }
}
