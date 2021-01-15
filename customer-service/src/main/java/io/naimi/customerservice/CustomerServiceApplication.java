package io.naimi.customerservice;

import com.netflix.discovery.converters.Auto;
import io.naimi.customerservice.Entity.Customer;
import io.naimi.customerservice.Repositoriy.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class CustomerServiceApplication implements CommandLineRunner {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private RepositoryRestConfiguration repositoryRestConfiguration;
    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        repositoryRestConfiguration.exposeIdsFor(Customer.class);
        customerRepository.save(new Customer(null,"Souhayl Naimi","souhayl.naimi@gmail.com"));
        customerRepository.save(new Customer(null,"Soukaina Mabchour","soukaina.mabchour@gmail.com"));
        customerRepository.save(new Customer(null,"Mustapha Naimi","mustapha.naimi@gmail.com"));
        customerRepository.save(new Customer(null,"Mina Mekkaoui","mina.mekkaoui@gmail.com"));
        customerRepository.findAll().forEach(customer -> {
            System.out.println(customer.toString());
        });
    }
}
