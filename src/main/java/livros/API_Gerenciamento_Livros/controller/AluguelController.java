package livros.API_Gerenciamento_Livros.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import livros.API_Gerenciamento_Livros.Service.AluguelService;
import livros.API_Gerenciamento_Livros.model.DTO.AluguelDTO;
import livros.API_Gerenciamento_Livros.model.DTO.DevolucaoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/biblioteca")
@Tag(name = "Aluguel de Livros", description = "Operação de aluguel de livros, realugar e devolução do livro.")
public class AluguelController {

    @Autowired
    private AluguelService aluguelService;

    @PostMapping("/{id}/{copiaId}")
    @Operation(summary = "Aluguel de livro vai salva o usuário que alugou o livro e qual foi o livro alugado.")
    public ResponseEntity addAluguel(@PathVariable("id") String id, @PathVariable("copiaId") String copiaId) {
        this.aluguelService.setAluguel(id, copiaId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/{copiaId}/realugar")
    @Operation(summary = "Realugar o livro, o máximo que pode realugar o livro é de 10 vezes.")
    public ResponseEntity realugar(@PathVariable("id") String id, @PathVariable("copiaId") String copiaId) {
        this.aluguelService.realugar(id, copiaId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/{livroId}/devolucao")
    @Operation(summary = "A devolução do livro.")
    public ResponseEntity realizacao_devolucao(@RequestBody @Valid DevolucaoDTO dto,
                                               @PathVariable("livroId")  String livroId,
                                               @PathVariable("id") String id
    ) {
        this.aluguelService.putAluguel(id, livroId, dto);
        return ResponseEntity.ok().build();
    }
}
