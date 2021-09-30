package co.wordbe.jpashop.repository.product;

import co.wordbe.jpashop.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
