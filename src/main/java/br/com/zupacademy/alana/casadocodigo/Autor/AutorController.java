package br.com.zupacademy.alana.casadocodigo.Autor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("autores")
public class AutorController {

    private final AutorRepository autorRepository;

    public AutorController(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    @PostMapping
    public ResponseEntity<AutorDTO> adicionarAutor(@RequestBody @Valid AutorForm autorForm){
        System.out.println(autorForm);
        Autor autor = autorForm.converterParaAutor();
        Autor autorSalved = autorRepository.save(autor);
        return ResponseEntity.ok().body(new AutorDTO(autorSalved));
    }
}
