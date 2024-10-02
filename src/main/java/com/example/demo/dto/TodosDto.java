package com.example.demo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class TodosDto {

    private int currentPageNumber;
    private int size;
    private boolean hasNext;
    private List<TodoDto> todos;
}
