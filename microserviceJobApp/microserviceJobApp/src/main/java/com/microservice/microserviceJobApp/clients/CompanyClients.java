package com.microservice.microserviceJobApp.clients;


import com.microservice.microserviceJobApp.external.Company;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "Microservice-Company")
public interface CompanyClients {

    @GetMapping("/companies/{Id}")
  Company getCompany(@PathVariable("Id") Long Id);
}
