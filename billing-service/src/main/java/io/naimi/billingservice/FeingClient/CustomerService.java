package io.naimi.billingservice.FeingClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "CUSTOMER-SERVICE")
public interface CustomerService {
    @RequestMapping(method = RequestMethod.GET, value = "/customers/{id}")
    public Customer findCustomerById(@PathVariable("id") Long id);
}

