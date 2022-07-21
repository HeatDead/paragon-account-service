package com.example.paragonaccountservice.Controllers;

import com.example.paragonaccountservice.Objects.Account;
import com.example.paragonaccountservice.Objects.Car;
import com.example.paragonaccountservice.Objects.RepairOrder;
import com.example.paragonaccountservice.Services.AccountService;
import lombok.RequiredArgsConstructor;
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

    @RequestMapping(method = RequestMethod.GET)
    public Account getAccountInfo(@RequestHeader HttpHeaders request)  throws Exception{
        String authHeader = request.getFirst(HttpHeaders.AUTHORIZATION);

        if (!authHeader.startsWith("Bearer ")){
            System.out.println(authHeader + " - " + "no 'Bearer'");
            return null;
        }

        String token = authHeader.substring(7);
        return accountService.getUserInfo(token);
    }

    @RequestMapping(value = "/cars", method = RequestMethod.GET)
    public List<Car> getUserCars(@RequestHeader HttpHeaders request) throws Exception{
        String authHeader = request.getFirst(HttpHeaders.AUTHORIZATION);

        if (!authHeader.startsWith("Bearer "))
            throw new AuthenticationException(authHeader + " - " + "no 'Bearer'");

        String token = authHeader.substring(7);
        return accountService.getUserCars(token);
    }

    @RequestMapping(value = "/checkCar", method = RequestMethod.GET)
    public boolean belongCarToUser(@RequestParam Long id, @RequestHeader HttpHeaders request) throws Exception{
        String authHeader = request.getFirst(HttpHeaders.AUTHORIZATION);

        if (!authHeader.startsWith("Bearer "))
            throw new AuthenticationException(authHeader + " - " + "no 'Bearer'");

        String token = authHeader.substring(7);
        return accountService.belongCarToUser(id, token);
    }

    @RequestMapping(value = "/repairOrders", method = RequestMethod.GET)
    public List<RepairOrder> getUserRepairOrders(@RequestHeader HttpHeaders request) throws Exception{
        String authHeader = request.getFirst(HttpHeaders.AUTHORIZATION);

        if (!authHeader.startsWith("Bearer "))
            throw new AuthenticationException(authHeader + " - " + "no 'Bearer'");

        String token = authHeader.substring(7);
        return accountService.getUserRepairOrders(token, false);
    }

    @RequestMapping(value = "/finishedRepairOrders", method = RequestMethod.GET)
    public List<RepairOrder> getUserFinishedRepairOrders(@RequestHeader HttpHeaders request)  throws Exception{
        String authHeader = request.getFirst(HttpHeaders.AUTHORIZATION);

        if (!authHeader.startsWith("Bearer "))
            throw new AuthenticationException(authHeader + " - " + "no 'Bearer'");

        String token = authHeader.substring(7);
        return accountService.getUserRepairOrders(token, true);
    }
}