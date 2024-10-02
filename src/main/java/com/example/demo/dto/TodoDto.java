package com.example.demo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TodoDto {

    private int id;
    private String content;
    private boolean completed;
}
