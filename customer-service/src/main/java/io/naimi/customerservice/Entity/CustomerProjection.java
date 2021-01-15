package io.naimi.customerservice.Entity;

import io.naimi.customerservice.Entity.Customer;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "p1",types = Customer.class)
public interface CustomerProjection {
    public Long getId();
    public String getName();
}
