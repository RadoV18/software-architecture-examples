package bo.edu.ucb.todo.exception;

import bo.edu.ucb.todo.dto.ResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(TodoNotFoundException.class)
    public ResponseEntity<ResponseDto<Void>> handleTodoNotFoundException(TodoNotFoundException ex) {
        ResponseDto<Void> responseDto = new ResponseDto<>(null, ex.getMessage(), false);
        return ResponseEntity.status(404).body(responseDto);
    }
}
