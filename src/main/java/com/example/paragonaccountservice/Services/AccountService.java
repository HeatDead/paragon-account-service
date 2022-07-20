package com.example.paragonaccountservice.Services;

import com.example.paragonaccountservice.Objects.Account;
import com.example.paragonaccountservice.Objects.Car;
import com.example.paragonaccountservice.Objects.RepairOrder;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface AccountService {
    Account getUserInfo(String token) throws Exception;
    List<Car> getUserCars(String token) throws Exception;

    boolean belongCarToUser(@RequestParam Long id, String token) throws Exception;

    List<RepairOrder> getUserRepairOrders(String token, boolean state) throws Exception;
}
