package co.wordbe.jpashop.service;

import co.wordbe.jpashop.domain.item.Book;
import co.wordbe.jpashop.domain.item.Item;
import co.wordbe.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    @Transactional
    public void updateItem(Long itemId, Book bookParam) {
        Item item = itemRepository.findOne(itemId);
        item.setPrice(bookParam.getPrice());
        item.setName(bookParam.getName());
        item.setStockQuantity(bookParam.getStockQuantity());
    }
}
