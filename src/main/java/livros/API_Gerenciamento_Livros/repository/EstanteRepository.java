package livros.API_Gerenciamento_Livros.repository;

import livros.API_Gerenciamento_Livros.model.entity.Estante;
import livros.API_Gerenciamento_Livros.model.entity.Pratileira;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EstanteRepository extends JpaRepository<Estante, Integer> {
    Optional<Estante> findByAreaAtuacao(String areaAtuacao);
}
