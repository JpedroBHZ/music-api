package br.com.jpedrobhz.music_api.service;

import br.com.jpedrobhz.music_api.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
// Implementa UserDetailsService para o Spring saber que essa classe gerencia a busca de credenciais
public class AuthorizationService implements UserDetailsService {

    private final UserRepository repository;

    // Injeção do repositório para consultar o banco de dados
    public AuthorizationService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Quando alguém tentar logar, o Spring Security chama esse método automaticamente
        return repository.findByLogin(username);
    }
}