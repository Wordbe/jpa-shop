package co.wordbe.jpashop.repository.simpleOrder;

import co.wordbe.jpashop.domain.Address;
import co.wordbe.jpashop.domain.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderSimpleQueryDto {
    private Long orderId;
    private String name;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private Address address;

    public OrderSimpleQueryDto(Long id, String name, LocalDateTime orderDate, OrderStatus status, Address address) {
        this.orderId = id;
        this.name = name; //LAZY 초기화
        this.orderDate = orderDate;
        this.orderStatus = status;
        this.address = address; //LAZY 초기화
    }
}
