package io.naimi.billingservice.FeingClient;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Product {
    private Long id;
    private String name;
    private double price;
}
