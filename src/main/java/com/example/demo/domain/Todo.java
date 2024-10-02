package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Todo {
    @Id
    private Long id;
    private int userId;
    private String title;
    private Boolean completed;

}
