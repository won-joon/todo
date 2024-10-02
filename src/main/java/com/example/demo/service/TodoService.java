package com.example.demo.service;

import com.example.demo.domain.Todo;
import com.example.demo.dto.TodoDto;
import com.example.demo.dto.TodosDto;
import com.example.demo.repository.TodoRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.*;
import java.security.SecureRandom;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    private final JavaMailSender javaMailSender;

    public TodosDto getTodos(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Todo> todoPage = todoRepository.findAll(pageable);

        List<TodoDto> todoDtos = todoPage.getContent().stream().map(todo -> {
            TodoDto todoDto = new TodoDto();
            todoDto.setId(todo.getId());
            todoDto.setContent(todo.getTitle());
            todoDto.setCompleted(todo.getCompleted());
            return todoDto;
        }).toList();

        TodosDto todosDto = new TodosDto();
        todosDto.setCurrentPageNumber(page);
        todosDto.setSize(size);
        todosDto.setHasNext(todoPage.hasNext());
        todosDto.setTodos(todoDtos);

        return todosDto;
    }

    private String generateCode() {
        // 임시 코드 생성 로직 (6자리 랜덤 문자열)
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder tempCode = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            tempCode.append(characters.charAt(random.nextInt(characters.length())));
        }
        System.out.println("임시 코드 : " + tempCode.toString());
        return tempCode.toString();
    }

    public void getEmailAuth(String email) {
        String tempCode = generateCode();

        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(email);
            helper.setSubject("이메일 인증 안내");
            helper.setText("<p>안녕하세요,</p><p>임시 코드는 다음과 같습니다: <strong>" + tempCode + "</strong></p><p>로그인 후 비밀번호를 변경해 주세요.</p>", true);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
