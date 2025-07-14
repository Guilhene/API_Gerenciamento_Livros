package livros.API_Gerenciamento_Livros.Service;

import livros.API_Gerenciamento_Livros.model.DTO.AuthenticatioDTO;
import livros.API_Gerenciamento_Livros.model.DTO.CadastroDTO;
import livros.API_Gerenciamento_Livros.model.DTO.LoginDTO;
import livros.API_Gerenciamento_Livros.model.entity.Usuario;
import livros.API_Gerenciamento_Livros.repository.UsuarioRepository;
import livros.API_Gerenciamento_Livros.security.TokenSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CadastroService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenSecurity tokenSecurity;

    public String cadastroUsuario(CadastroDTO dto){
        String encryptedPassword = new BCryptPasswordEncoder().encode(dto.password());

        Usuario newUsuario = new Usuario(null, dto.name(), dto.email(), encryptedPassword, dto.role());
        this.usuarioRepository.save(newUsuario);
        return "";
    }

    public AuthenticatioDTO loginUsuario(LoginDTO dto){

        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.email(), dto.password());

        var authentication = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenSecurity.generateToken((Usuario) authentication.getPrincipal());

        var user = (Usuario) authentication.getPrincipal();

        return new AuthenticatioDTO(user.getUsuarioId(), token);
    }
}
