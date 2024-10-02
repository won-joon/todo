package com.example.demo.dto.response;

import lombok.Getter;

@Getter
public class AuthResponse {
    private boolean isSuccess;

    public AuthResponse(boolean isSuccess){
        this.isSuccess = isSuccess;
    }
}
