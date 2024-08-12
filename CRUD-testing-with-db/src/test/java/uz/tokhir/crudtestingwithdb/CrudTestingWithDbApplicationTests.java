package uz.tokhir.crudtestingwithdb;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.client.RestTemplate;
import uz.tokhir.crudtestingwithdb.entity.Product;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@SpringBootTest
class CrudTestingWithDbApplicationTests {

    @LocalServerPort
    private int port;

    private String baseUrl = "http://localhost:" + port;

    private static RestTemplate restTemplate;

    @Autowired
    private TestH2Repository h2Repository;

    @BeforeAll
    public static void init(){
        restTemplate = new RestTemplate();
    }

    @BeforeEach
    public void setUp(){
        baseUrl=baseUrl.concat("/products");
    }

    @Test
    public void testAddProduct(){
        Product product = new Product("mouse",2,444);
        Product response = restTemplate.postForObject(baseUrl, product, Product.class);
        assertEquals("mouse",response.getName());
        assertEquals(1,h2Repository.findAll().size());
    }

    @Test
    @Sql(statements = "INSERT INTO PRODUCT_TBL (id, name, quantity, price) VALUES (4,'phone',1,1229)",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "DELETE FROM PRODUCT_TBL WHERE name='phone'",executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testGetProducts(){
        List<Product> products = restTemplate.getForObject(baseUrl, List.class);
        assertEquals(1,products.size());
        assertEquals(1,h2Repository.findAll().size());
    }

    @Test
    @Sql(statements = "INSERT INTO PRODUCT_TBL (id, name, quantity, price) VALUES (1,'notebook',1,119)",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "DELETE FROM PRODUCT_TBL WHERE id=1",executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testFindProductById(){
        Product product = restTemplate.getForObject(baseUrl+"/{id}", Product.class,1);
        assertAll(
                ()->assertNotNull(product),
                ()->assertEquals(1,product.getId()),
                ()->assertEquals("notebook",product.getName())
        );

    }

    @Test
    @Sql(statements = "INSERT INTO PRODUCT_TBL (id, name, quantity, price) VALUES (2,'cup',1,229)",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "DELETE FROM PRODUCT_TBL WHERE id=2",executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testUpdateProduct(){
        Product product = new Product("cup",1,2229);
        restTemplate.put(baseUrl+"/update/{id}", product,2);
        Product productFromDB = h2Repository.findById(2).get();
        assertAll(
                ()->assertNotNull(product),
                ()->assertEquals(2229,product.getPrice())
        );
    }

    @Test
    @Sql(statements = "INSERT INTO PRODUCT_TBL (id, name, quantity, price) VALUES (3,'pen',1,49)",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void testDeleteProduct(){
        int recordCount = h2Repository.findAll().size();
        assertEquals(1,recordCount);
        restTemplate.delete(baseUrl+"/delete/{id}",3);
        assertEquals(0,h2Repository.findAll().size());
    }

}
