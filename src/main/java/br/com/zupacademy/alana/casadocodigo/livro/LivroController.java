package br.com.zupacademy.alana.casadocodigo.livro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/livros")
public class LivroController {

    @PersistenceContext
    EntityManager manager;

    @Autowired
    private LivroRepository livroRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<LivroDTO> cadastraLivro(@RequestBody @Valid LivroForm livroForm){
        Livro livro = livroForm.converterParaLivro(manager);
        manager.persist(livro);
        return ResponseEntity.ok().body(new LivroDTO(livro));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<LivroDTOList> listarLivros(){
        List<Livro> livros = livroRepository.findAll();
        List<LivroDTOList> livrosDTO = new ArrayList<>();
        livros.stream().forEach(e -> {
            livrosDTO.add(new LivroDTOList(e));
        });

        return livrosDTO;
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivroDTO> detalharLivro(@PathVariable Long id){
        Optional<Livro> livro = livroRepository.findById(id);
        if( livro.isPresent() ) return ResponseEntity.ok().body(new LivroDTO(livro.get()));

        return ResponseEntity.notFound().build();
    }

}
