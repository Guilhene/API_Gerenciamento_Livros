package livros.API_Gerenciamento_Livros.repository;

import livros.API_Gerenciamento_Livros.model.entity.Aluguel;
import livros.API_Gerenciamento_Livros.model.entity.UsuarioCopiaLivroId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AluguelRepository extends JpaRepository<Aluguel, UsuarioCopiaLivroId> {
}
