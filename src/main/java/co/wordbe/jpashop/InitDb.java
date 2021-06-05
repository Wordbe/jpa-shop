package co.wordbe.jpashop;

import co.wordbe.jpashop.domain.*;
import co.wordbe.jpashop.domain.item.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@RequiredArgsConstructor
@Component
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void InitDb() {
        initService.doInit1();
        initService.doInit2();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;

        public void doInit1() {
            Member member = createMember("kim","서울", "강", "21345");
            em.persist(member);

            Book book1 = createBook("카산드라", 10000, 100);
            em.persist(book1);
            Book book2 = createBook("몽고디비", 12000, 100);
            em.persist(book2);

            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 10000, 1);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 12000, 2);

            Delivery delivery = new Delivery();
            delivery.setAddress(member.getAddress());
            Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
            em.persist(order);
        }

        public void doInit2() {
            Member member = createMember("lee", "성남", "별", "31345");
            em.persist(member);

            Book book1 = createBook("녹용", 20000, 100);
            em.persist(book1);
            Book book2 = createBook("동충하초", 40000, 100);
            em.persist(book2);

            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 20000, 3);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 40000, 4);

            Delivery delivery = new Delivery();
            delivery.setAddress(member.getAddress());
            Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
            em.persist(order);
        }

        private Member createMember(String name, String city, String street, String zipcode) {
            Member member = new Member();
            member.setName(name);
            member.setAddress(new Address(city, street, zipcode));
            return member;
        }

        private Book createBook(String name, int price, int stockQuantity) {
            Book book = new Book();
            book.setName(name);
            book.setPrice(price);
            book.setStockQuantity(stockQuantity);
            return book;
        }
    }
}
