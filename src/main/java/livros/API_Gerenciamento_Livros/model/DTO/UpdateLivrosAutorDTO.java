package livros.API_Gerenciamento_Livros.model.DTO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import java.util.Date;

public record UpdateLivrosAutorDTO(
        String titulo,
        String descricao,
        String tema,
        Integer volume,
        Date dataCriacao,
        String areaAtuacao
) {
}
