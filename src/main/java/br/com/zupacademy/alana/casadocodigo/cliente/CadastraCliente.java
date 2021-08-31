package br.com.zupacademy.alana.casadocodigo.cliente;

import br.com.zupacademy.alana.casadocodigo.estado.Estado;
import br.com.zupacademy.alana.casadocodigo.estado.EstadoRepository;
import br.com.zupacademy.alana.casadocodigo.paises.PaisRepository;
import br.com.zupacademy.alana.casadocodigo.validators.Exceptions.ErroFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class CadastraCliente {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PaisRepository paisRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    @PostMapping
    public ResponseEntity<?> cadastraCliente(@RequestBody @Validated ClienteRequest clienteRequest){
        List<Estado> estadosByPais = estadoRepository.findByPais_Id(clienteRequest.getIdPais());
        Cliente cliente;

        if(estadosByPais.isEmpty()) {
            cliente = clienteRequest.toModel(paisRepository);
        }
        else if(clienteRequest.getIdEstado() == null){
            ErroFormDTO erro = new ErroFormDTO("idEstado", "O id referente ao estado é obrigatório");
            return ResponseEntity.badRequest().body(erro);
        }
        else{
            Optional<Estado> estado = estadoRepository.findById(clienteRequest.getIdEstado());
            if(estado.isEmpty()){
                ErroFormDTO erro = new ErroFormDTO("idEstado", "Não existe estado com o id recebido");
                return ResponseEntity.badRequest().body(erro);
            }
            if(estado.get().getPais().getId() != clienteRequest.getIdPais()){
                ErroFormDTO erro = new ErroFormDTO("idEstado", "Estado não pertence ao Pais selecionado");
                return ResponseEntity.badRequest().body(erro);
            }
            cliente = clienteRequest.toModel(paisRepository, estadoRepository);
        }
        cliente = clienteRepository.save(cliente);
        return ResponseEntity.ok().body(new ClienteResponse(cliente));
    }


}
