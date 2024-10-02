package com.example.demo.controller;

import com.example.demo.dto.TodosDto;
import com.example.demo.dto.request.AuthRequest;
import com.example.demo.dto.request.EmailRequest;
import com.example.demo.dto.response.AuthResponse;
import com.example.demo.dto.response.EmailResponse;
import com.example.demo.service.TodoService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @GetMapping("/todos")
    public ResponseEntity<TodosDto> travellerPage(@RequestParam("size") int size, @RequestParam("page") int page) {

        return ResponseEntity.ok(todoService.getTodos(page, size));
    }

    @PostMapping("/email")
    public ResponseEntity<EmailResponse> emailAuth(@RequestBody EmailRequest emailRequest, HttpServletRequest request){

        return ResponseEntity.ok().body(todoService.getEmailAuth(emailRequest.getEmail(), request));
    }

    @PostMapping("/authentication")
    public ResponseEntity<AuthResponse> authCheck(@RequestBody AuthRequest authRequest, HttpServletRequest request){
        return ResponseEntity.ok().body(todoService.checkCode(authRequest.getAuthentication(), request));
    }
}
