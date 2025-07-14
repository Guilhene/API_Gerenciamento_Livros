package livros.API_Gerenciamento_Livros.repository;

import livros.API_Gerenciamento_Livros.model.entity.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivrosRepository extends JpaRepository<Livro, Integer> {
}
