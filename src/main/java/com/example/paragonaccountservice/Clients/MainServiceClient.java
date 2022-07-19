package com.example.paragonaccountservice.Clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Objects;


@FeignClient(value = "paragon-main-service", url = "http://localhost:8080/")
public interface MainServiceClient {
    @RequestMapping(method = RequestMethod.GET, value = "/cars/carsOf")
    List<Object> getAllCarsOfUser(@RequestParam("owner") String owner);
}