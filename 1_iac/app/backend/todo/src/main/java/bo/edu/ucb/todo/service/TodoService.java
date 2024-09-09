package bo.edu.ucb.todo.service;

import bo.edu.ucb.todo.dto.TodoReqDto;
import bo.edu.ucb.todo.dto.TodoResDto;

import java.util.List;

public interface TodoService {
    TodoResDto create(TodoReqDto t);
    TodoResDto findById(Long id);
    List<TodoResDto> findAll();
    TodoResDto update(TodoReqDto t, Long id);
    void delete(Long id);
}
