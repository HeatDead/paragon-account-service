package com.example.paragonaccountservice.Objects;

import lombok.Data;
import lombok.Value;

@Data
public class Account {
    String username;
    String name;
    String surname;
    String patronymic;
}