package livros.API_Gerenciamento_Livros.repository;

import livros.API_Gerenciamento_Livros.model.entity.CopiaLivro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CopiaLivroRepository extends JpaRepository<CopiaLivro, Integer> {
}
