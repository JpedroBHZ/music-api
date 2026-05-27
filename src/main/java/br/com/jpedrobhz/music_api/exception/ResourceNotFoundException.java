package br.com.jpedrobhz.music_api.exception;

/**
 * Exceção customizada para quando um recurso (Música, Setlist, Evento) não for encontrado no banco de dados.
 * Herda de RuntimeException para que o Spring consiga capturar o erro em tempo de execução.
 */
public class ResourceNotFoundException extends RuntimeException {

    // Construtor que recebe a mensagem personalizada de erro e repassa para a classe pai
    public ResourceNotFoundException(String msg) {
        super(msg);
    }
}