package com.example.paragonaccountservice.Clients;

import com.example.paragonaccountservice.Objects.RepairOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "paragon-orders-service", url = "http://localhost:8082/")
public interface OrdersServiceClient {
    @RequestMapping(method = RequestMethod.GET, value = "/orders/repairOrders")
    List<RepairOrder> getRepairOrders(@RequestHeader HttpHeaders request);

    @RequestMapping(method = RequestMethod.GET, value = "/orders/finishedRepairOrders")
    List<RepairOrder> getFinishedRepairOrders(@RequestHeader HttpHeaders request);
}
