package bo.edu.ucb.todo.mapper;

import bo.edu.ucb.todo.dto.TodoResDto;
import bo.edu.ucb.todo.entity.Todo;

import java.util.ArrayList;
import java.util.List;

public class TodoMapper {
    public static TodoResDto toResDto(Todo todo) {
        return TodoResDto.builder()
                .id(todo.getId())
                .title(todo.getTitle())
                .description(todo.getDescription())
                .createDate(todo.getCreateDate())
                .done(todo.getDone())
                .build();
    }

    public static List<TodoResDto> toResDtoList(List<Todo> todos) {
        List<TodoResDto> todoResDtos = new ArrayList<>();
        for(Todo todo : todos) {
            todoResDtos.add(toResDto(todo));
        }
        return todoResDtos;
    }
}
