package livros.API_Gerenciamento_Livros.model.DTO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import java.util.Date;

public record LivroDTO(
        String titulo,
        String descricao,
        String tema,
        int volume,
        Date dataCriacao,
        String areaAtuacao,
        @Min(value =  1, message = "Nao poder ser negativo")
        @Max(value = 4, message = "A quantidade maxima de pratileira e 4.")
        Integer identificacao
) {
}


