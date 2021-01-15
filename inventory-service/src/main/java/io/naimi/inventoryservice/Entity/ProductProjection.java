package io.naimi.inventoryservice.Entity;


import io.naimi.inventoryservice.Entity.Product;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "p1",types = Product.class)
public interface ProductProjection {
    public Long getId();
    public String getName();
}
