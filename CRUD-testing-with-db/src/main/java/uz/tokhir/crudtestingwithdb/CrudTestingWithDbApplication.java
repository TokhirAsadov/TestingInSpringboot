package uz.tokhir.crudtestingwithdb;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import uz.tokhir.crudtestingwithdb.repository.ProductRepository;

@SpringBootApplication
public class CrudTestingWithDbApplication {

    @Autowired
    private ProductRepository repository;

    @PostConstruct
    public void init() {
//        repository.saveAll(
//                Stream
//                        .of(
//                                new Product("Book", 1, 299),
//                                new Product("mobile", 1, 39999))
//                        .collect(Collectors.toList())
//        );
    }

    public static void main(String[] args) {
        SpringApplication.run(CrudTestingWithDbApplication.class, args);
    }

}
