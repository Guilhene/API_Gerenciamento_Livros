package livros.API_Gerenciamento_Livros.controller;

import ch.qos.logback.core.model.processor.PhaseIndicator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import livros.API_Gerenciamento_Livros.Service.BibliotecaService;
import livros.API_Gerenciamento_Livros.model.DTO.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/biblioteca")
@Tag(name = "Controller para livros, Autores, estante e pratileira", description = "operação para o cadastro de livros e autores, listagem de dados, atualizar os dados e delete de dados")
public class BibliotecaController {

    @Autowired
    private BibliotecaService bibliotecaService;

    @PostMapping("/addAutor")
    @Operation(summary = "Adicionar autor.")
    public ResponseEntity addAutor(@RequestBody @Valid AutorDTO dto) {
        var newAutor = bibliotecaService.addAutor(dto);
        return ResponseEntity.ok("Id: " + newAutor);
    }

    @DeleteMapping("/{id}/deleteautor")
    @Operation(summary = "deleta autor pelo id do autor.")
    public ResponseEntity deleteAutor(@PathVariable("id") String id) {
        this.bibliotecaService.deleteAutor(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/addLivro")
    @Operation(summary = "adicionar livro, estante e pratileira.")
    public ResponseEntity addLivro(@PathVariable("id") String id, @RequestBody @Valid LivroDTO dto) {

        var newLivro = bibliotecaService.addLivro(id, dto);
        return ResponseEntity.ok(newLivro);
    }

    @GetMapping("/listaAutores")
    @Operation(summary = "Lista autores.")
    public ResponseEntity listaAutores() {
        var listaAutores = bibliotecaService.listaAutores();
        return ResponseEntity.ok(listaAutores);
    }

    @GetMapping("/{id}/listaAutorId")
    @Operation(summary = "Retorna dados do autor pelo id do autor.")
    public ResponseEntity listaAutoresId(@PathVariable("id") String id) {
        var autorId = bibliotecaService.ListAutorId(id);
        return ResponseEntity.ok(autorId);
    }

    @PutMapping("/{id}/updateAutor")
    @Operation(summary = "Atualizar dados do autor.")
    public ResponseEntity upDateAutor(@PathVariable("id") String id, @RequestBody @Valid AutorDTO dto) {
        this.bibliotecaService.upDateAutores(id, dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/listaLivros")
    @Operation(summary = "Lista livros.")
    public ResponseEntity listaLivros() {
        return ResponseEntity.ok(bibliotecaService.listaLivros());
    }

    @GetMapping("/{id}/listaLivroId")
    @Operation(summary = "Retorna dados do livro pelo id do livro.")
    public ResponseEntity listaLivrosId(@PathVariable("id") String id) {
        var listaLivro = bibliotecaService.ListLivroId(id);
        return ResponseEntity.ok(listaLivro);
    }

    @PutMapping("/{id}/updatelivro")
    @Operation(summary = "Atualizar dados do livro.")
    public ResponseEntity upDateLivros(@PathVariable("id") String id, @RequestBody @Valid UpdateLivrosAutorDTO dto) {
        this.bibliotecaService.updateLivro(id, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}/deletelivro")
    @Operation(summary = "Deleta livro pelo id do livro.")
    public ResponseEntity deleteLivro(@PathVariable("id") String id) {
        this.bibliotecaService.deleteLivro(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/{estanteId}")
    @Operation(summary = "Atualizar a estante que o livro esta.")
    public ResponseEntity upDateEstantePratileira(@PathVariable("id") String id, @PathVariable("estanteId") String estanteId, @RequestBody @Valid UpDateEstantePratileiraDTO dto) {
        this.bibliotecaService.upDateEstante(id, estanteId,dto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/{estanteId}/{pratileiraId}")
    @Operation(summary = "Atualizar a pratileira que o livro esta")
    public ResponseEntity upDatePratileira(@PathVariable("id") String id,
                                           @PathVariable("estanteId") String estanteId,
                                           @PathVariable("pratileiraId") String pratileiraId,
                                           @RequestBody @Valid UpDatePratileiraDTO dto
    ) {
        this.bibliotecaService.upDatePratileira(id, estanteId, pratileiraId, dto);
        return ResponseEntity.ok().build();
    }



}
