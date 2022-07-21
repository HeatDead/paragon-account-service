package com.example.paragonaccountservice.Clients;

import com.example.paragonaccountservice.Objects.Car;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@FeignClient(value = "paragon-main-service", url = "http://mainService:8080/")
public interface MainServiceClient {
    @RequestMapping(method = RequestMethod.GET, value = "/cars/carsOf")
    List<Car> getAllCarsOfUser(@RequestParam("owner") String owner);
}