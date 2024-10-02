package com.example.demo.domain;

import lombok.Getter;

@Getter
public class Todo {
    private int userId;
    private int id;
    private String title;
    private Boolean completed;

}
