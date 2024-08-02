package uz.tokhir.crudtestingwithdb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.tokhir.crudtestingwithdb.entity.Product;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    Product findByName(String name);
}

