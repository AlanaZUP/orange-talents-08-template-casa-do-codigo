package br.com.zupacademy.alana.casadocodigo.Livro;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/livros")
public class LivroController {

    @PersistenceContext
    EntityManager manager;

    @PostMapping
    @Transactional
    public ResponseEntity<LivroDTO> cadastraLivro(@RequestBody @Valid LivroForm livroForm){
        Livro livro = livroForm.converterParaLivro(manager);
        manager.persist(livro);
        return ResponseEntity.ok().body(new LivroDTO(livro));
    }
}
