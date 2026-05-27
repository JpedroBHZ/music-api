package br.com.jpedrobhz.music_api.model.security;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "tb_users")
// Implementamos UserDetails para o Spring Security reconhecer essa classe como o modelo de usuario do sistema
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String login;

    @Column(nullable = false)
    private String password;

    // Perfil simplificado: Armazena apenas a String "ADMIN" ou "USER"
    private String role;

    // Construtor padrão exigido pelo JPA
    public User() {
    }

    // Construtor completo para usarmos no cadastro
    public User(String login, String password, String role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }

    // --- METODOS OBRIGATORIOS DO USERDETAILS (SPRING SECURITY) ---

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Se for ADMIN, recebe permissao de ADMIN e de USER. Se nao, recebe apenas USER.
        if (this.role.equals("ADMIN")) {
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        } else {
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        }
    }

    @Override
    public String getPassword() {
        return this.password; // Indica qual o campo de senha da entidade
    }

    @Override
    public String getUsername() {
        return this.login; // Indica qual o campo de login/username da entidade
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Conta expirada? Nao (true)
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Conta bloqueada? Nao (true)
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Senha expirada? Nao (true)
    }

    @Override
    public boolean isEnabled() {
        return true; // Usuario ativo? Sim (true)
    }

    // --- GETTERS E SETTERS PADRAO ---

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }
    public void setPassword(String password) { this.password = password; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}