package likelion.assignment6.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDetailDto {
    private Long actualPaymentAmount;
    private LocalDateTime paymentDate;

}
