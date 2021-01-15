package io.naimi.inventoryservice;

import io.naimi.inventoryservice.Entity.Product;
import io.naimi.inventoryservice.Repositoriy.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class InventoryServiceApplication implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private RepositoryRestConfiguration repositoryRestConfiguration;
    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        repositoryRestConfiguration.exposeIdsFor(Product.class);
        productRepository.save(new Product(null,"Dell XPS 9300",19800.00));
        productRepository.save(new Product(null,"ThinkPad X1 Carbon 6Th Gen",21700.00));
        productRepository.save(new Product(null,"Macbook Pro 16",28100.00));
        productRepository.save(new Product(null,"Razor Blade Pro 15",36100.00));

        productRepository.findAll().forEach(product -> {
            System.out.println(product.toString());
        });
    }
}
