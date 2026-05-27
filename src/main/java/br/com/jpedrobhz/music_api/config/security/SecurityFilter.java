package br.com.jpedrobhz.music_api.config.security;

import org.springframework.stereotype.Component;
import br.com.jpedrobhz.music_api.repository.UserRepository;
import br.com.jpedrobhz.music_api.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component // Avisa ao Spring para gerenciar este filtro
// OncePerRequestFilter garante que esse filtro rode exatamente uma vez a cada requisição que chega na API
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final UserRepository userRepository;

    public SecurityFilter(TokenService tokenService, UserRepository userRepository) {
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 1. Recupera o token que veio no cabeçalho 'Authorization' da requisição
        String token = this.recoverToken(request);

        if (token != null) {
            // 2. Valida o token e pega o login do usuário
            String login = tokenService.validateToken(token);

            // 3. Se achou o login, busca o usuário completo no banco de dados
            UserDetails user = userRepository.findByLogin(login);

            if (user != null) {
                // 4. Cria o objeto de autenticação que o Spring Security exige e injeta ele no contexto do sistema
                var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        // 5. Continua o fluxo normal da requisição (passa para a Controller ou próximo filtro)
        filterChain.doFilter(request, response);
    }

    // Método auxiliar para cortar a palavra "Bearer " e pegar apenas a String pura do Token JWT
    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) return null;
        return authHeader.replace("Bearer ", "");
    }
}