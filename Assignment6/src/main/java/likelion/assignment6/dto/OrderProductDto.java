package likelion.assignment6.dto;

import likelion.assignment6.entity.OrderStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class OrderProductDto {
    private Long orderProductId;
    private Long orderId;
    private Long cancelId;
    private String productName;
    private Long productPrice;
    private Long productAmount;
    private OrderStatus productStatus;
}