package co.wordbe.jpashop.service;

import co.wordbe.jpashop.domain.product.Product;
import co.wordbe.jpashop.repository.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    @Transactional
    public void process1(Product product) {
        productRepository.deleteAll();
        productRepository.save(product);
        throw new RuntimeException("상품 생성 취소");
    }

    @Transactional
    public void process2(Product product) {
        deleteAndCreate(product);
    }

    public void process3(Product product) {
        deleteAndCreate(product);
    }

    @Transactional
    public void deleteAndCreate(Product product) {
        productRepository.deleteAll();
        productRepository.save(product);
        throw new RuntimeException("상품 생성 취소");
    }
}
