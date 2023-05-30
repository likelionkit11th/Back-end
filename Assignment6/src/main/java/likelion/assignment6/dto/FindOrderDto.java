package likelion.assignment6.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class FindOrderDto {
    private Long orderId;
    private Long memberId;
    private String orderName;
    private Long orderPrice;
    private LocalDate orderDate;
    private List<OrderProductDto> orderProducts;
}