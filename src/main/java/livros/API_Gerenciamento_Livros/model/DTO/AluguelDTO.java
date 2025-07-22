package livros.API_Gerenciamento_Livros.model.DTO;

import javax.xml.crypto.Data;
import java.util.Date;

public record AluguelDTO(
        Date dt_emprestado,
        Date dt_devolucao,
        Date dt_devolucaoRealizada
) {
}
