package com.example.paragonaccountservice.Services;

import com.example.paragonaccountservice.Objects.Account;

public interface AccountService {
    Account getUserInfo(String token);
}
