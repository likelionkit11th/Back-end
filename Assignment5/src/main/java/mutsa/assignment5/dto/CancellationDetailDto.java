package mutsa.assignment5.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mutsa.assignment5.entity.OrderStatus;
import mutsa.assignment5.entity.PaymentMethod;

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
