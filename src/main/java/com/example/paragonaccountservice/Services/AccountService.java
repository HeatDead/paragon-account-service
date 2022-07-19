package com.example.paragonaccountservice.Services;

import com.example.paragonaccountservice.Objects.Account;
import com.example.paragonaccountservice.Objects.RepairOrder;

import java.util.List;

public interface AccountService {
    Account getUserInfo(String token);
    List<Object> getUserCars(String token) throws Exception;
    List<RepairOrder> getUserRepairOrders(String token, boolean state) throws Exception;
}
