package io.naimi.billingservice.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.naimi.billingservice.FeingClient.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Bill {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate billingDate;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long customerID;
    @Transient
    private Customer customer;
    @OneToMany(mappedBy = "bill")
    private Collection<ProductItem> productItems;
}
