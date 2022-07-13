package com.example.paragonaccountservice.Services;

import com.example.paragonaccountservice.Objects.Account;

import java.util.List;

public interface AccountService {
    Account getUserInfo(String token);
    List<Object> getUserCars(String token);
}
