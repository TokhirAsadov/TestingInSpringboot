package uz.tokhir.crudtestingwithdb;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CrudTestingWithDbApplicationTests {

    @LocalServerPort
    private int port;

    private String baseUrl = "http://localhost:" + port;

    private static RestTemplate restTemplate;

    @BeforeAll
    public static void init(){
        restTemplate = new RestTemplate();
    }

    @BeforeEach
    public void setUp(){
        baseUrl=baseUrl.concat("/products");
    }

}
