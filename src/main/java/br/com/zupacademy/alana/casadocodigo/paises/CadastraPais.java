package br.com.zupacademy.alana.casadocodigo.paises;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/paises")
public class CadastraPais {

    @Autowired
    private PaisRepository paisRepository;

    @PostMapping
        @ResponseStatus(HttpStatus.CREATED)
    public PaisResponse cadastroPais(@RequestBody @Validated PaisRequest paisRequest){
        Pais pais = paisRequest.toModel();
        return new PaisResponse(paisRepository.save(pais));
    }
}
