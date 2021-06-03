package co.wordbe.jpashop.service;

import co.wordbe.jpashop.domain.item.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;

@SpringBootTest
public class ItemUpdateTest {

    @Autowired
    EntityManager em;

    @Test
    public void updateTest() throws Exception {
//        Book book = em.find(Book.class, 1L);

        // TX
//        book.setName("바뀐 이름");

        // 변경감지 (dirty checking)
        // TX commit
    }
}
