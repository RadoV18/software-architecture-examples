package bo.edu.ucb.todo.exception;

import bo.edu.ucb.todo.dto.ResponseDto;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(TodoNotFoundException.class)
    public ResponseDto<Void> handleTodoNotFoundException(TodoNotFoundException ex) {
        return ResponseDto.<Void>builder()
                .data(null)
                .message(ex.getMessage())
                .success(false)
                .build();
    }
}
