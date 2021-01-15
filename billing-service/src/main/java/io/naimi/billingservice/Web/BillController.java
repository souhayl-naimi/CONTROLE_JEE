package io.naimi.billingservice.Web;

import io.naimi.billingservice.Entities.Bill;
import io.naimi.billingservice.FeingClient.CustomerService;
import io.naimi.billingservice.FeingClient.InventoryService;
import io.naimi.billingservice.Repositories.BillRepository;
import io.naimi.billingservice.Repositories.ProductItemRepository;
import javafx.application.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;

@RestController
public class BillController {
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private ProductItemRepository productItemRepository;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private InventoryService inventoryService;

    @RequestMapping(method = RequestMethod.GET, value = "/fullBill/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Bill getBill(@PathVariable("id") Long id){
        Bill bill = billRepository.findById(id).get();
        bill.setCustomer(customerService.findCustomerById(bill.getCustomerID()));
        bill.getProductItems().forEach(productItem -> {
            productItem.setProduct(inventoryService.findProductById(productItem.getProductID()));
        });
        return bill;
    }
}
