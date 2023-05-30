package likelion.assignment6.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import likelion.assignment6.entity.OrderStatus;
import likelion.assignment6.entity.PaymentMethod;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CancellationDetailDto {

    private Long cancelId;
    private Long orderId;
    private Long orderPrice;
    private PaymentMethod paymentMethod;
    private OrderStatus orderStatus;
    private LocalDate canceledDate;

}
