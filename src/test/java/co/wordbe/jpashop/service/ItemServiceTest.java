package co.wordbe.jpashop.service;

import co.wordbe.jpashop.domain.item.Book;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
class ItemServiceTest {

    @Autowired
    ItemService itemService;

    @Autowired
    EntityManager em;

    @Test
    public void 상품추가_조회() throws Exception {
        //given
        Book book = new Book();
        book.setAuthor("베르베르");
        book.setIsbn("A1B2");
        book.addStock(1);

        //when
        itemService.saveItem(book);
        
        //then
        assertEquals(book.getStockQuantity(), itemService.findOne(book.getId()).getStockQuantity());
    }

    @Test
    @DisplayName("상품변경")
    public void 상품변경() throws Exception {
        //given
        Book book = new Book();
        book.setAuthor("베르베르");
        book.setIsbn("A1B2");
        book.addStock(1);
        itemService.saveItem(book);

        //when
        // 상품 취소
        book.removeStock(1);
        em.flush();

        //then
        assertEquals(book.getStockQuantity(), itemService.findOne(book.getId()).getStockQuantity());
    }
}