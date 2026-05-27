package br.com.jpedrobhz.music_api.config;

import br.com.jpedrobhz.music_api.config.security.SecurityFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // 1. Declare o filtro como um atributo final
    private final SecurityFilter securityFilter;

    // 2. Crie o construtor para o Spring injetar o filtro automaticamente
    public SecurityConfig(SecurityFilter securityFilter) {
        this.securityFilter = securityFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                // Desabilita a proteção contra CSRF porque nossa API é Stateless (usa Tokens)
                .csrf(csrf -> csrf.disable())
                // Define que a aplicação não salvará sessões no servidor (padrão REST JWT)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // Configuração das regras de acesso às rotas
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()    // Qualquer um tenta logar
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll() // Qualquer um se cadastra
                        .requestMatchers(HttpMethod.GET, "/api/**").permitAll()        // Qualquer um lê músicas/eventos/setlists
                        .anyRequest().authenticated()                                  // Criar, deletar ou editar exige login!
                )
                // 3. Agora a variável securityFilter vai funcionar perfeitamente aqui:
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        // Gerenciador de autenticação exigido pelo Spring para processar as tentativas de login
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Define o algoritmo BCrypt para hash e criptografia das senhas no banco
        return new BCryptPasswordEncoder();
    }
}