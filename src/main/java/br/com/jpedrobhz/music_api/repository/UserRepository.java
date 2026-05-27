package br.com.jpedrobhz.music_api.repository;

import br.com.jpedrobhz.music_api.model.security.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Long> {

    // Método que o Spring Security usa para buscar o usuário pelo texto do login
    UserDetails findByLogin(String login);
}