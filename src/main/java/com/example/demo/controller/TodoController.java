package com.example.demo.controller;

import com.example.demo.dto.TodosDto;
import com.example.demo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @GetMapping("/todos")
    public ResponseEntity<TodosDto> travellerPage(@RequestParam("size") int size, @RequestParam("page") int page) throws IOException {

        return ResponseEntity.ok(todoService.getTodos(page, size));
    }

    @GetMapping("/data")
    public void saveData(){
        todoService.saveData();
    }

}
