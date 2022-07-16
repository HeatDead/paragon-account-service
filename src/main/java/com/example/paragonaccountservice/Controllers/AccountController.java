package com.example.paragonaccountservice.Controllers;

import com.example.paragonaccountservice.Objects.Account;
import com.example.paragonaccountservice.Services.AccountService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import java.util.List;

@RestController
@RequestMapping("/account")
@CrossOrigin
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @GetMapping
    public Account getAccountInfo(@RequestHeader HttpHeaders request){
        String authHeader = request.getFirst(HttpHeaders.AUTHORIZATION);

        if (!authHeader.startsWith("Bearer ")){
            System.out.println(authHeader + " - " + "no 'Bearer'");
            return null;
        }

        String token = authHeader.substring(7);
        return accountService.getUserInfo(token);
    }

    @GetMapping("/cars")
    public List<Object> getUserCars(@RequestHeader HttpHeaders request) throws Exception{
        String authHeader = request.getFirst(HttpHeaders.AUTHORIZATION);

        if (!authHeader.startsWith("Bearer "))
            throw new AuthenticationException(authHeader + " - " + "no 'Bearer'");

        String token = authHeader.substring(7);
        return accountService.getUserCars(token);
    }
}
