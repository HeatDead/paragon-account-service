package com.example.paragonaccountservice.Controllers;

import com.example.paragonaccountservice.Objects.Account;
import com.example.paragonaccountservice.Services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/account")
@CrossOrigin
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PostMapping
    public Account getAccountInfo(@RequestHeader HttpHeaders request){
        String authHeader = request.getFirst(HttpHeaders.AUTHORIZATION);

        if (!authHeader.startsWith("Bearer ")){
            System.out.println(authHeader + " - " + "no 'Bearer'");
            return null;
        }

        String token = authHeader.substring(7);
        return accountService.getUserInfo(token);
    }
}
