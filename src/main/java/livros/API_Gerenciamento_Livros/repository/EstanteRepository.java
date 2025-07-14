package livros.API_Gerenciamento_Livros.repository;

import livros.API_Gerenciamento_Livros.model.entity.Estante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstanteRepository extends JpaRepository<Estante, Integer> {
}
