package livros.API_Gerenciamento_Livros.controller;

import jakarta.persistence.Id;
import jakarta.validation.Valid;
import livros.API_Gerenciamento_Livros.Service.BibliotecaService;
import livros.API_Gerenciamento_Livros.model.DTO.AutorDTO;
import livros.API_Gerenciamento_Livros.model.DTO.LivroDTO;
import livros.API_Gerenciamento_Livros.model.entity.Livro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/biblioteca")
public class BibliotecaController {

    @Autowired
    private BibliotecaService bibliotecaService;

    @PostMapping("/addAutor")
    public ResponseEntity addAutor(@RequestBody @Valid AutorDTO dto) {
        var newAutor = bibliotecaService.addAutor(dto);
        return ResponseEntity.ok("Id: " + newAutor);
    }

    @PostMapping("/{id}/addLivro")
    public ResponseEntity addLivro(@PathVariable("id") String id, @RequestBody @Valid LivroDTO dto) {

        var newLivro = bibliotecaService.addLivro(id, dto);
        return ResponseEntity.ok(newLivro);
    }



}
