package livros.API_Gerenciamento_Livros.controller;

import jakarta.validation.Valid;
import livros.API_Gerenciamento_Livros.Service.AluguelService;
import livros.API_Gerenciamento_Livros.model.DTO.AluguelDTO;
import livros.API_Gerenciamento_Livros.model.DTO.DevolucaoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/biblioteca")
public class AluguelController {

    @Autowired
    private AluguelService aluguelService;

    @PostMapping("/{id}/{copiaId}")
    public ResponseEntity addAluguel(@PathVariable("id") String id, @PathVariable("copiaId") String copiaId) {
        this.aluguelService.setAluguel(id, copiaId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/{copiaId}/realugar")
    public ResponseEntity realugar(@PathVariable("id") String id, @PathVariable("copiaId") String copiaId) {
        this.aluguelService.realugar(id, copiaId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/{livroId}/devolucao")
    public ResponseEntity realizacao_devolucao(@RequestBody @Valid DevolucaoDTO dto,
                                               @PathVariable("livroId")  String livroId,
                                               @PathVariable("id") String id
    ) {
        this.aluguelService.putAluguel(id, livroId, dto);
        return ResponseEntity.ok().build();
    }
}
