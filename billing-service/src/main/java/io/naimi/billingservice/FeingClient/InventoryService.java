package io.naimi.billingservice.FeingClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "INVENTORY-SERVICE")
public interface InventoryService {
    @RequestMapping(method = RequestMethod.GET, value = "/products/{id}")
    public Product findProductById(@PathVariable("id") Long id);
    @RequestMapping(method = RequestMethod.GET, value = "/products")
    public PagedModel<Product> findAllProducts();
}

