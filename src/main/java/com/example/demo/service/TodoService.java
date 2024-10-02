package com.example.demo.service;

import com.example.demo.domain.Todo;
import com.example.demo.dto.TodoDto;
import com.example.demo.dto.TodosDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;

@Service
public class TodoService {

    private List<Todo> loadTodosFromJson() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("src/main/resources/data.json");
        return objectMapper.readValue(file, new TypeReference<List<Todo>>() {});
    }

    public TodosDto getTodos(int page, int size) throws IOException {
        List<Todo> todos = loadTodosFromJson();

        int start = page * size;
        int end = Math.min((start + size), todos.size());

        List<Todo> pageContent = todos.subList(start, end);

        List<TodoDto> todoDtos = pageContent.stream().map(todo -> {
            TodoDto todoDto = new TodoDto();
            todoDto.setId(todo.getId());
            todoDto.setContent(todo.getTitle());
            todoDto.setCompleted(todo.getCompleted());
            return todoDto;
        }).toList();

        TodosDto response = new TodosDto();
        response.setCurrentPageNumber(page);
        response.setSize(size);
        response.setHasNext(end < todos.size());
        response.setTodos(todoDtos);

        return response;
    }

}
