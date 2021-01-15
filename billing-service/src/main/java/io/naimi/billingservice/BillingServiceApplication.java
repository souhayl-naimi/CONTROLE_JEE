package io.naimi.billingservice;

import io.naimi.billingservice.Entities.Bill;
import io.naimi.billingservice.Entities.ProductItem;
import io.naimi.billingservice.FeingClient.Customer;
import io.naimi.billingservice.FeingClient.CustomerService;
import io.naimi.billingservice.FeingClient.InventoryService;
import io.naimi.billingservice.FeingClient.Product;
import io.naimi.billingservice.Repositories.BillRepository;
import io.naimi.billingservice.Repositories.ProductItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.hateoas.PagedModel;

import java.time.LocalDate;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication implements CommandLineRunner {

    @Autowired
    private BillRepository billRepository;
    @Autowired
    private ProductItemRepository productItemRepository;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private InventoryService inventoryService;


    public static void main(String[] args) {
        SpringApplication.run(BillingServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Customer customer = customerService.findCustomerById(1L);
        System.out.println("*******************");
        System.out.println(customer.toString());
        System.out.println("*******************");
        Bill bill = billRepository.save(new Bill(null, LocalDate.now(), customer.getId(),null,null));
        PagedModel<Product> products = inventoryService.findAllProducts();
        products.getContent().forEach(product -> {
            productItemRepository.save(new ProductItem(null, product.getId(), product.getName(), product.getPrice(), new Random().nextInt(30), null,bill));
        });

    }
}
