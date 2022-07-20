package com.example.paragonaccountservice.Objects;

import com.example.paragonaccountservice.Enums.Condition;
import lombok.Data;

@Data
public class Car {
    Long id;
    int year;
    Double price;
    Brand brand;
    Model model;
    String img_url;
    boolean sold;
    Condition condition;
}
