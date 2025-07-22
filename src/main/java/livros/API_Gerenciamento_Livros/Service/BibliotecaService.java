package livros.API_Gerenciamento_Livros.Service;

import jakarta.validation.Valid;
import livros.API_Gerenciamento_Livros.model.DTO.*;
import livros.API_Gerenciamento_Livros.model.entity.*;
import livros.API_Gerenciamento_Livros.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

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

    @Autowired
    private CopiaLivroRepository  copiaLivroRepository;

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

    public void deleteAutor(String id) {
        var autorId = autorRepository.findById(Integer.parseInt(id)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        this.autorRepository.delete(autorId);
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
                null,
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

        CopiaLivro copiaLivro = new CopiaLivro(
                null,
                true,
                "Novo",
                null,
                newLivro,
                newEstante,
                newPratileira
        );

        this.copiaLivroRepository.save(copiaLivro);

        return livroId.getLivroId();
    }

    public List listaAutores() {
        return this.autorRepository.findAll();
    }

    public Autor ListAutorId(String id){
        return this.autorRepository.findById(Integer.parseInt(id)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public void upDateAutores(String id, AutorDTO dto) {
        var autorId = autorRepository.findById(Integer.parseInt(id)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (dto.name() != null) {
            autorId.setNome(dto.name());
        }
        if (dto.descricao() != null) {
            autorId.setDescricao(dto.descricao());
        }
        if (dto.pais() != null) {
            autorId.setPais(dto.pais());
        }
        this.autorRepository.save(autorId);
    }

    public List listaLivros() {
        return this.livrosRepository.findAll();
    }

    public Livro ListLivroId(String id){
        return this.livrosRepository.findById(Integer.parseInt(id)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public void updateLivro(String id, @Valid UpdateLivrosAutorDTO dto) {
        var livroId = livrosRepository.findById(Integer.parseInt(id)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Map<Function<UpdateLivrosAutorDTO, String>, Consumer<String>> upDate = Map.of(
                UpdateLivrosAutorDTO::titulo, livroId::setTitulo,
                UpdateLivrosAutorDTO::descricao, livroId::setDescricao,
                UpdateLivrosAutorDTO::tema, livroId::setTema,
                UpdateLivrosAutorDTO::areaAtuacao, livroId::setAreaAtuacao
        );

        upDate.forEach((getter, setter) -> {
            String value = getter.apply(dto);
            if (value != null) {
                setter.accept(value);
            }
        });

        if (dto.volume() != null) {
            livroId.setVolume(dto.volume());
        }
        if (dto.dataCriacao() != null) {
            livroId.setDataCriacao(dto.dataCriacao());
        }
        this.livrosRepository.save(livroId);
    }

    public void deleteLivro(String id) {
        var livroId = livrosRepository.findById(Integer.parseInt(id)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        this.livrosRepository.delete(livroId);
    }

    public void upDateEstante(String id, String estanteId, UpDateEstantePratileiraDTO dto){
        this.livrosRepository.findById(Integer.parseInt(id)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        var estante = estanteRepository.findById(Integer.parseInt(estanteId)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (dto.areaAtuacao() != null) {
            estante.setAreaAtuacao(dto.areaAtuacao());
        }
    }

    public void upDatePratileira(String id, String estanteId, String pratileiraId, UpDatePratileiraDTO dto) {
        this.livrosRepository.findById(Integer.parseInt(id)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        this.estanteRepository.findById(Integer.parseInt(estanteId)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        var pratileira = pratileiraRepository.findById(Integer.parseInt(pratileiraId)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (dto.identificacao() != null){
            pratileira.setIdentificacao(dto.identificacao());
        }
    }
}
