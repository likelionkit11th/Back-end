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
public class PaymentDetailDto {

    private Long paymentDetailId;
    private Long orderedId;
    private PaymentMethod paymentMethod;
    private Long actualPaymentAmount;
    private OrderStatus orderStatus;
    private LocalDate paymentDate;

}
