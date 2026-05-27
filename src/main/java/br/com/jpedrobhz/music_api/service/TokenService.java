package br.com.jpedrobhz.music_api.service;

import java.util.Date;
import br.com.jpedrobhz.music_api.model.security.User;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    // Gera uma chave secreta segura para assinar o Token digitalmente
    private final SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // Método que gera o Token JWT atualizado para Date
    public String generateToken(User user) {
        return Jwts.builder()
                .issuer("music-api")
                .subject(user.getLogin())
                .issuedAt(Date.from(Instant.now()))
                .expiration(generateExpirationDate())
                .signWith(secretKey)
                .compact();
    }

    // Alterado o retorno de Instant para java.util.Date
    private Date generateExpirationDate() {
        Instant instant = LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
        return Date.from(instant); // Convertendo para Date antes de retornar
    }

    // Método que valida o Token enviado nas requisições e devolve o login do usuário se estiver tudo certo
    // Método de validação atualizado para a versão 0.12.x do JJWT
    public String validateToken(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload()
                    .getSubject();
        } catch (JwtException e) {
            return ""; // Se o token estiver inválido ou expirado, retorna vazio
        }
    }
}