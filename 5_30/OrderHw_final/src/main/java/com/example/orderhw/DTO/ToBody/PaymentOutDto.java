package com.example.orderhw.DTO.ToBody;

import com.example.orderhw.DTO.Create.PaymentDto;
import com.example.orderhw.Domain.Order;
import com.example.orderhw.Status.PaymentLogStatus;
import com.example.orderhw.Status.PaymentLogTool;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
@Getter
@NoArgsConstructor
public class PaymentOutDto {

    private Long id;

    private Long orderId;

    private PaymentLogTool paymentTool;

    private Long paymentPriceActual;

    private PaymentLogStatus paymentStatus;

    private LocalDateTime paymentTime;

    @Builder
    private PaymentOutDto(Long id,Long orderId, PaymentLogTool paymentTool, Long paymentPriceActual, PaymentLogStatus paymentStatus, LocalDateTime paymentTime){
        this.id=id;
        this.orderId=orderId;
        this.paymentTool=paymentTool;
        this.paymentPriceActual=paymentPriceActual;
        this.paymentStatus=paymentStatus;
        this.paymentTime=paymentTime;
    }

}
