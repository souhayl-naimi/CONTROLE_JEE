package io.naimi.billingservice.Entities;

import io.naimi.billingservice.Entities.Bill;
import io.naimi.billingservice.Entities.ProductItem;
import org.springframework.data.rest.core.config.Projection;

import java.time.LocalDate;
import java.util.Collection;

@Projection(name = "fullBill",types = Bill.class)
public interface BillProjection {
    public Long getId();
    public LocalDate getBillingDate();
    public Long getCustomerID();
    public Collection<ProductItem> getProductItems();
}
