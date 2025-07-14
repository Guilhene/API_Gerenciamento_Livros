package livros.API_Gerenciamento_Livros.controller;

import jakarta.validation.Valid;
import livros.API_Gerenciamento_Livros.Service.CadastroService;
import livros.API_Gerenciamento_Livros.model.DTO.AuthenticatioDTO;
import livros.API_Gerenciamento_Livros.model.DTO.CadastroDTO;
import livros.API_Gerenciamento_Livros.model.DTO.LoginDTO;
import livros.API_Gerenciamento_Livros.model.entity.Usuario;
import livros.API_Gerenciamento_Livros.repository.UsuarioRepository;
import livros.API_Gerenciamento_Livros.security.TokenSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/biblioteca")
public class CadastroController {

    @Autowired
    private CadastroService cadastroService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginDTO loginDTO) {
        var token = cadastroService.loginUsuario(loginDTO);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/createrUser")
    public ResponseEntity cadastro(@RequestBody @Valid CadastroDTO dto) {
        var newUsuario = cadastroService.cadastroUsuario(dto);
        return ResponseEntity.created(URI.create("/biblioteca/createrUser")).body(newUsuario);
    }
}
