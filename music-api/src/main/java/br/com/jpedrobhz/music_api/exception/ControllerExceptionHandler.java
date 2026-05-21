package br.com.jpedrobhz.music_api.exception;

import br.com.jpedrobhz.music_api.dto.StandardError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

//@ControllerAdvice: Avisa ao Sping que esta classe vai interceptar erros globais da controller
@ControllerAdvice
public class ControllerExceptionHandler {

    //@ExceptionHandler: Diz ao Spring que este metodo deve rodar especificamente quando houver erro de validação(@Valid)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest request){

        //Pega o status HTTP que queremos devolver (400 - Bad Request)
        HttpStatus status = HttpStatus.BAD_REQUEST;

        //captura a mensagem real de erro que escrevemos la no Model (ex: "O titulo da musica é obrigatório")
        String errorMessage = e.getBindingResult().getFieldError().getDefaultMessage();

        //Monta o nosso molde de erro customizado com dados limpos
        StandardError err = new StandardError(
                Instant.now(),
                status.value(),
                "Erro de validacao",
                errorMessage
        );

        //Retorna a resposta limpa e elegante para o cliente/Front-end
        return ResponseEntity.status(status).body(err);
    }
}
