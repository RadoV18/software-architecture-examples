package bo.edu.ucb.todo.service.impl;

import bo.edu.ucb.todo.dto.TodoReqDto;
import bo.edu.ucb.todo.dto.TodoResDto;
import bo.edu.ucb.todo.entity.Todo;
import bo.edu.ucb.todo.exception.TodoNotFoundException;
import bo.edu.ucb.todo.mapper.TodoMapper;
import bo.edu.ucb.todo.repository.TodoRepository;
import bo.edu.ucb.todo.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;

    @Override
    public TodoResDto create(TodoReqDto todoReqDto) {
        Todo newTodo = new Todo.Builder()
                .title(todoReqDto.getTitle())
                .description(todoReqDto.getDescription())
                .done(todoReqDto.getDone())
                .build();
        Todo savedTodo = todoRepository.save(newTodo);
        return TodoMapper.toResDto(savedTodo);
    }

    @Override
    public TodoResDto findById(Long id) {
        Todo todo = getTodoById(id);
        return TodoMapper.toResDto(todo);
    }

    @Override
    public List<TodoResDto> findAll() {
        List<Todo> todos = todoRepository.findAll();
        return TodoMapper.toResDtoList(todos);
    }

    @Override
    public TodoResDto update(TodoReqDto todoReqDto, Long id) {
        Todo todo = getTodoById(id);
        todo.setTitle(todoReqDto.getTitle());
        todo.setDescription(todoReqDto.getDescription());
        todo.setDone(todoReqDto.getDone());
        Todo updatedTodo = todoRepository.save(todo);
        return TodoMapper.toResDto(updatedTodo);
    }

    @Override
    public void delete(Long id) {
        Todo todo = getTodoById(id);
        todoRepository.delete(todo);
    }

    private Todo getTodoById(Long id) {
        Optional<Todo> todo = todoRepository.findById(id);
        if(todo.isEmpty()) {
            throw new TodoNotFoundException("Todo not found");
        }
        return todo.get();
    }
}
