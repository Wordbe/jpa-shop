package co.wordbe.jpashop.api;

import co.wordbe.jpashop.domain.Address;
import co.wordbe.jpashop.domain.Order;
import co.wordbe.jpashop.domain.OrderStatus;
import co.wordbe.jpashop.repository.OrderRepository;
import co.wordbe.jpashop.repository.OrderSearch;
import co.wordbe.jpashop.repository.simpleOrder.OrderSimpleQueryDto;
import co.wordbe.jpashop.repository.simpleOrder.OrderSimpleQueryRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * xToOne
 * (ManyToOne, OneToOne)
 * Order -> Member (ManyToOne)
 * Order -> Delivery (OneToOne)
 */
@RequiredArgsConstructor
@RestController
public class OrderSimpleApiController {

    private final OrderRepository orderRepository;
    private final OrderSimpleQueryRepository orderSimpleQueryRepository;

    @GetMapping("/api/v1/simple-orders")
    public List<Order> simpleOrders() {
        List<Order> orders = orderRepository.findAllByJPQL(new OrderSearch());
        for (Order order : orders) {
            order.getMember().getName(); // LAZY 로딩에서 강제로 데이터 불러오기
            order.getDelivery().getStatus(); // LAZY 로딩에서 강제로 데이터 불러오기
        }
        return orders;
    }

    @GetMapping("/api/v2/simple-orders")
    public List<SimpleOrderDto> simpleOrders2() {
        return orderRepository.findAllByJPQL(new OrderSearch())
                .stream()
                .map(SimpleOrderDto::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/api/v3/simple-orders")
    public List<SimpleOrderDto> simpleOrders3() {
        return orderRepository.findAllWithMemberDelivery()
                .stream()
                .map(SimpleOrderDto::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/api/v4/simple-orders")
    public List<OrderSimpleQueryDto> simpleOrders4() {
        return orderSimpleQueryRepository.findOrderDtos();
    }

    @Data
    static class SimpleOrderDto {
        private Long orderId;
        private String name;
        private LocalDateTime orderDate;
        private OrderStatus orderStatus;
        private Address address;

        public SimpleOrderDto(Order order) {
            this.orderId = order.getId();
            this.name = order.getMember().getName(); //LAZY 초기화
            this.orderDate = order.getOrderDate();
            this.orderStatus = order.getStatus();
            this.address = order.getDelivery().getAddress(); //LAZY 초기화
        }
    }

}
