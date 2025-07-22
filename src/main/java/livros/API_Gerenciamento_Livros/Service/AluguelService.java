package livros.API_Gerenciamento_Livros.Service;

import livros.API_Gerenciamento_Livros.model.DTO.AluguelDTO;
import livros.API_Gerenciamento_Livros.model.DTO.DevolucaoDTO;
import livros.API_Gerenciamento_Livros.model.entity.Aluguel;
import livros.API_Gerenciamento_Livros.model.entity.CopiaLivro;
import livros.API_Gerenciamento_Livros.model.entity.Usuario;
import livros.API_Gerenciamento_Livros.model.entity.UsuarioCopiaLivroId;
import livros.API_Gerenciamento_Livros.repository.AluguelRepository;
import livros.API_Gerenciamento_Livros.repository.CopiaLivroRepository;
import livros.API_Gerenciamento_Livros.repository.LivrosRepository;
import livros.API_Gerenciamento_Livros.repository.UsuarioRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Date;

@Service
public class AluguelService {

    @Autowired
    private AluguelRepository aluguelRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private LivrosRepository livrosRepository;

    @Autowired
    private CopiaLivroRepository copiaLivroRepository;

    public void setAluguel(String id, String copiaId) {
        var user = usuarioRepository.findById(Integer.parseInt(id)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        var copia = copiaLivroRepository.findById(Integer.parseInt(copiaId)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        var livro = livrosRepository.findById(Integer.parseInt(copiaId)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (copia.getDisponivel() != false) {

            copia.setDisponivel(false);
            copia.setLivro(livro);
            this.copiaLivroRepository.save(copia);

            UsuarioCopiaLivroId aluguelId = new UsuarioCopiaLivroId();
            aluguelId.setUsuarioId(user.getUsuarioId());
            aluguelId.setCopiaLivroId(copia.getId());

            Aluguel aluguel = new Aluguel();
            aluguel.setUsuarioCopiaLivroId(aluguelId);
            aluguel.setUsuario(user);
            aluguel.setCopiaLivro(copia);
            aluguel.setDt_devolucao(LocalDate.now().plusDays(8));
            aluguel.setDt_devolucao_realizada(null);
            aluguel.setRealugar(1);

            this.aluguelRepository.save(aluguel);
        }
        if (copia.getDisponivel() != true) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    public void realugar(String id, String copiaId) {
        UsuarioCopiaLivroId aluguelId = new UsuarioCopiaLivroId();
        aluguelId.setUsuarioId(Integer.parseInt(id));
        aluguelId.setCopiaLivroId(Integer.parseInt(copiaId));

        var aluguel = aluguelRepository.findById(aluguelId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (aluguel.getRealugar() <= 10) {
            aluguel.setRealugar(aluguel.getRealugar() + 1);
            LocalDate novaData = aluguel.getDt_devolucao().plusDays(8);
            aluguel.setDt_devolucao(novaData);
            aluguelRepository.save(aluguel);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Esse livro nao pode ser realizado o aluguel");
        }
    }

    public void putAluguel(String id, String livroId, DevolucaoDTO dto) {
        var user = usuarioRepository.findByEmail(dto.email());
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        var copia =  copiaLivroRepository.findById(Integer.parseInt(livroId)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        copia.setDisponivel(true);

        UsuarioCopiaLivroId aluguelId = new UsuarioCopiaLivroId();
        aluguelId.setUsuarioId(Integer.parseInt(id));
        aluguelId.setCopiaLivroId(Integer.parseInt(livroId));


        var aluguel = aluguelRepository.findById(aluguelId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        LocalDate novaData = aluguel.getDt_devolucao_realizada();
        aluguel.setDt_devolucao_realizada(novaData);

        copiaLivroRepository.save(copia);
        aluguelRepository.save(aluguel);
    }
}
