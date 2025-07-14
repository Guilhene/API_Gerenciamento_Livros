package livros.API_Gerenciamento_Livros.Service;

import jakarta.validation.Valid;
import livros.API_Gerenciamento_Livros.model.DTO.AutorDTO;
import livros.API_Gerenciamento_Livros.model.DTO.LivroDTO;
import livros.API_Gerenciamento_Livros.model.entity.Autor;
import livros.API_Gerenciamento_Livros.model.entity.Estante;
import livros.API_Gerenciamento_Livros.model.entity.Livro;
import livros.API_Gerenciamento_Livros.model.entity.Pratileira;
import livros.API_Gerenciamento_Livros.repository.AutorRepository;
import livros.API_Gerenciamento_Livros.repository.EstanteRepository;
import livros.API_Gerenciamento_Livros.repository.LivrosRepository;
import livros.API_Gerenciamento_Livros.repository.PratileiraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;

@Service
public class BibliotecaService {

    @Autowired
    private LivrosRepository livrosRepository;

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private EstanteRepository estanteRepository;

    @Autowired
    private PratileiraRepository pratileiraRepository;

    public Integer addAutor(@Valid AutorDTO dto) {

        Autor autor = new Autor(
                null,
                dto.name(),
                dto.descricao(),
                dto.pais(),
                new ArrayList<>()
        );

        var autorId = this.autorRepository.save(autor);

        return autorId.getAutorId();

    }

    public Integer addLivro(String id, @Valid LivroDTO dto) {

        var autorId = autorRepository.findById(Integer.parseInt(id)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Livro newLivro = new Livro(
                null,
                dto.titulo(),
                dto.descricao(),
                dto.tema(),
                dto.volume(),
                dto.dataCriacao(),
                dto.areaAtuacao(),
                autorId,
                null
        );

        var livroId = livrosRepository.save(newLivro);


        var newEstante = new Estante(
                null,
                newLivro.getAreaAtuacao(),
                newLivro
        );

        this.estanteRepository.save(newEstante);

        Pratileira newPratileira = new Pratileira(
                dto.identificacao(),
                newEstante
        );

        if (newPratileira.getIdentificacao() > 5 ) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        this.pratileiraRepository.save(newPratileira);

        return livroId.getLivroId();
    }
}
