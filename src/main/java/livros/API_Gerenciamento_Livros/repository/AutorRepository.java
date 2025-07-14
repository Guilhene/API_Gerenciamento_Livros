package livros.API_Gerenciamento_Livros.repository;

import livros.API_Gerenciamento_Livros.model.entity.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository<Autor, Integer> {
}
