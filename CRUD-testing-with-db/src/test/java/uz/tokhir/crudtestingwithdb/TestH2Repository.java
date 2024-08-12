package uz.tokhir.crudtestingwithdb;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.tokhir.crudtestingwithdb.entity.Product;

public interface TestH2Repository extends JpaRepository<Product,Integer> {
}
