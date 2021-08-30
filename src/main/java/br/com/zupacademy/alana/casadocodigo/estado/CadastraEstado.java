package br.com.zupacademy.alana.casadocodigo.estado;

import br.com.zupacademy.alana.casadocodigo.estadoTeste.Estado;
import br.com.zupacademy.alana.casadocodigo.estadoTeste.EstadoRepository;
import br.com.zupacademy.alana.casadocodigo.estadoTeste.EstadoRequest;
import br.com.zupacademy.alana.casadocodigo.estadoTeste.EstadoResponse;
import br.com.zupacademy.alana.casadocodigo.paises.Pais;
import br.com.zupacademy.alana.casadocodigo.paises.PaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/estados")
public class CadastraEstado {

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private PaisRepository paisRepository;

    @PostMapping
    public ResponseEntity<br.com.zupacademy.alana.casadocodigo.estadoTeste.EstadoResponse> cadastraEstado(@RequestBody @Validated EstadoRequest estadoRequest){
        Pais pais = paisRepository.findById(estadoRequest.getIdPais()).get();
        List<br.com.zupacademy.alana.casadocodigo.estadoTeste.Estado> estadoFind = estadoRepository.findByNomeAndPais(estadoRequest.getNome(), pais);

        if(estadoFind.size() > 0){
            return ResponseEntity.badRequest().build();
        }

        Estado estado = estadoRequest.toModel(paisRepository);
        estado = estadoRepository.save(estado);
        return ResponseEntity.ok().body(new EstadoResponse(estado));
    }
}
