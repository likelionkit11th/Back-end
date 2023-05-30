package likelion.assignment6.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class OrderDto {
    private String orderName;
    private Long orderPrice;
    private LocalDateTime orderDate;

}
