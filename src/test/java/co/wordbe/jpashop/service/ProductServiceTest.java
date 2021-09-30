package co.wordbe.jpashop.service;

import co.wordbe.jpashop.domain.product.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;

@SpringBootTest
class ProductServiceTest {
    @Autowired ProductService productService;
    @Autowired EntityManager em;

    @Test
    void 하나의_트랜잭션안에서_실행된다() {
        Product product = Product.builder()
                .name("영양제1")
                .price(45000)
                .build();

        productService.process1(product);
    }

    @Test
    void 같은클래스안의_다른메소드호출시_하나의_트랜잭션안에서_실행된다() {
        Product product = Product.builder()
                .name("임팩타민2")
                .price(50000)
                .build();

        productService.process2(product);
    }

    @Test
    void 같은클래스안의_다른메소드호출시_하나의_트랜잭션안에서_실행되지_않는다() {
        Product product = Product.builder()
                .name("종근당")
                .price(100000)
                .build();

        productService.process3(product);
    }
}