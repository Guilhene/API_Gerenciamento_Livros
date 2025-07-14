package livros.API_Gerenciamento_Livros.repository;

import livros.API_Gerenciamento_Livros.model.entity.Aluguel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AluguelRepository extends JpaRepository<Aluguel, Integer> {
}
