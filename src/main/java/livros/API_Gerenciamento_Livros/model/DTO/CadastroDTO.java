package livros.API_Gerenciamento_Livros.model.DTO;

import livros.API_Gerenciamento_Livros.model.entity.UserRole;

public record CadastroDTO(
        String name,
        String email,
        String password,
        UserRole role
) {
}
