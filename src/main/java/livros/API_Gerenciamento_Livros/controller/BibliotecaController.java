package livros.API_Gerenciamento_Livros.controller;

import ch.qos.logback.core.model.processor.PhaseIndicator;
import jakarta.validation.Valid;
import livros.API_Gerenciamento_Livros.Service.BibliotecaService;
import livros.API_Gerenciamento_Livros.model.DTO.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/{id}/deleteautor")
    public ResponseEntity deleteAutor(@PathVariable("id") String id) {
        this.bibliotecaService.deleteAutor(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/addLivro")
    public ResponseEntity addLivro(@PathVariable("id") String id, @RequestBody @Valid LivroDTO dto) {

        var newLivro = bibliotecaService.addLivro(id, dto);
        return ResponseEntity.ok(newLivro);
    }

    @GetMapping("/listaAutores")
    public ResponseEntity listaAutores() {
        var listaAutores = bibliotecaService.listaAutores();
        return ResponseEntity.ok(listaAutores);
    }

    @GetMapping("/{id}/autor")
    public ResponseEntity listaAutoresId(@PathVariable("id") String id) {
        var autorId = bibliotecaService.ListAutorId(id);
        return ResponseEntity.ok(autorId);
    }

    @PutMapping("/{id}/updateAutor")
    public ResponseEntity upDateAutor(@PathVariable("id") String id, @RequestBody @Valid AutorDTO dto) {
        this.bibliotecaService.upDateAutores(id, dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/listaLivros")
    public ResponseEntity listaLivros() {
        return ResponseEntity.ok(bibliotecaService.listaLivros());
    }

    public ResponseEntity listaLivrosId(@PathVariable("id") String id) {
        var listaLivro = bibliotecaService.ListLivroId(id);
        return ResponseEntity.ok(listaLivro);
    }

    @PutMapping("/{id}/updatelivro")
    public ResponseEntity upDateLivros(@PathVariable("id") String id, @RequestBody @Valid UpdateLivrosAutorDTO dto) {
        this.bibliotecaService.updateLivro(id, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}/deletelivro")
    public ResponseEntity deleteLivro(@PathVariable("id") String id) {
        this.bibliotecaService.deleteLivro(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/{estanteId}")
    public ResponseEntity upDateEstantePratileira(@PathVariable("id") String id, @PathVariable("estanteId") String estanteId, @RequestBody @Valid UpDateEstantePratileiraDTO dto) {
        this.bibliotecaService.upDateEstante(id, estanteId,dto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/{estanteId}/{pratileiraId}")
    public ResponseEntity upDatePratileira(@PathVariable("id") String id,
                                           @PathVariable("estanteId") String estanteId,
                                           @PathVariable("pratileiraId") String pratileiraId,
                                           @RequestBody @Valid UpDatePratileiraDTO dto
    ) {
        this.bibliotecaService.upDatePratileira(id, estanteId, pratileiraId, dto);
        return ResponseEntity.ok().build();
    }



}
