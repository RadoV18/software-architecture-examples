package bo.edu.ucb.todo.controller;

import bo.edu.ucb.todo.dto.ResponseDto;
import bo.edu.ucb.todo.dto.TodoReqDto;
import bo.edu.ucb.todo.dto.TodoResDto;
import bo.edu.ucb.todo.service.TodoService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost")
@RestController
@RequestMapping("/api/v1/todo")
@AllArgsConstructor
public class TodoController {

    private static final Logger logger = LoggerFactory.getLogger(TodoController.class);
    private final TodoService todoService;

    @GetMapping
    public ResponseEntity<ResponseDto<List<TodoResDto>>> getAllTodos() {
        logger.info("GET /api/v1/todo");
        List<TodoResDto> result = todoService.findAll();
        return ResponseEntity.ok(
            ResponseDto.<List<TodoResDto>>builder()
                .data(result)
                .message("Todos fetched successfully")
                .success(true)
                .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<TodoResDto>> getById(
            @PathVariable Long id
    ) {
        logger.info("GET /api/v1/todo/{}", id);
        TodoResDto result = todoService.findById(id);
        return ResponseEntity.ok(
            ResponseDto.<TodoResDto>builder()
                .data(result)
                .message("Todo fetched successfully")
                .success(true)
                .build()
        );
    }

    @PostMapping
    public ResponseEntity<ResponseDto<TodoResDto>> createTodo(
            @RequestBody TodoReqDto todoReqDto
    ) {
        logger.info("POST /api/v1/todo");
        TodoResDto result = todoService.create(todoReqDto);
        return new ResponseEntity<ResponseDto<TodoResDto>>(
            ResponseDto.<TodoResDto>builder()
                .data(result)
                .message(null)
                .success(true)
                .build(),
            null,
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto<TodoResDto>> updateTodo(
            @PathVariable Long id,
            @RequestBody TodoReqDto todoReqDto
    ) {
        logger.info("PUT /api/v1/todo/{}", id);
        TodoResDto result = todoService.update(todoReqDto, id);
        return ResponseEntity.ok(
            ResponseDto.<TodoResDto>builder()
                .data(result)
                .message("Todo updated successfully")
                .success(true)
                .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(
            @PathVariable Long id
    ) {
        logger.info("DELETE /api/v1/todo/{}", id);
        todoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
