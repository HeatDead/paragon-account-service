package com.example.paragonaccountservice.Objects;

import lombok.Value;

@Value
public class User {
    String username;
    String password;

    String name;
    String surname;
    String patronymic;
}
