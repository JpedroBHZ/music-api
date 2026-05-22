package br.com.jpedrobhz.music_api.dto;

import java.time.Instant;

//Classe que serve de molde para padronizar as respostas de erro da nossa API
public class StandardError {

    //Momento exato em que o erro aconteceu
    private Instant timestamp;

    //Codigo http do erro (exp:400, 404, 500)
    private Integer status;

    //Um resumo curto do tipo de erro (ex: "Erro de validacao")
    private String error;

    //A mensagem amigavel explicando o que o usuário fez de errado
    private String message;

    //Construtor completo para podermos instaciar a classe com todos os dados
    public StandardError(Instant timestamp, Integer status, String error, String message) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
    }

    //Getters e Setters nnecessários para o Spring conseguir transformar a classe em JSON
    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
