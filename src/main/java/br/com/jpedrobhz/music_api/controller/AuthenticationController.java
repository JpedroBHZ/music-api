package br.com.jpedrobhz.music_api.controller;

import br.com.jpedrobhz.music_api.model.security.User;
import br.com.jpedrobhz.music_api.repository.UserRepository;
import br.com.jpedrobhz.music_api.service.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final TokenService tokenService;

    public AuthenticationController(AuthenticationManager authenticationManager, UserRepository userRepository, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    // Endpoint de Login: Autentica as credenciais e devolve o Token JWT
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> data) {
        // Encapusla o login e a senha digitados no padrão do Spring Security
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.get("login"), data.get("password"));

        // O AuthenticationManager vai no banco via AuthorizationService e valida se a senha bate
        var auth = this.authenticationManager.authenticate(usernamePassword);

        // Se deu certo, gera o token digital e devolve no JSON
        var token = tokenService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok(Map.of("token", token));
    }

    // Endpoint de Registro: Cria um novo usuário com senha criptografada em BCrypt
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> data) {
        // Verifica se o login já existe no banco
        if (this.userRepository.findByLogin(data.get("login")) != null) {
            return ResponseEntity.badRequest().body("Erro: Este login ja esta cadastrado.");
        }

        // Criptografa a senha usando o algoritmo BCrypt antes de salvar no banco
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.get("password"));

        // Cria a entidade preenchendo o login, a senha criptografada e o perfil padrão (USER)
        User newUser = new User(data.get("login"), encryptedPassword, "USER");

        // Salva o novo usuário no banco de dados do Postgres
        this.userRepository.save(newUser);
        return ResponseEntity.ok().build();
    }
}