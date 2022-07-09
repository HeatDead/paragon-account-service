package com.example.paragonaccountservice;

import lombok.Value;

@Value
public class UserRequest {
    String username;
    String password;
    String role;
}
