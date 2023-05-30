package com.example.orderhw.DTO;

import com.example.orderhw.Domain.Order;
import com.example.orderhw.Domain.OrderProduct;
import com.example.orderhw.Domain.PaymentLog;
import com.example.orderhw.Status.OrderProductStatus;
import com.example.orderhw.Status.PaymentLogStatus;
import com.example.orderhw.Status.PaymentLogTool;

public record PaymentDto(Order order , PaymentLogTool paymentTool, Long paymentPriceActual, PaymentLogStatus paymentStatus) {
    public PaymentLog toEntity(){
        return PaymentLog.builder()
                .order(order)
                .paymentTool(paymentTool)
                .paymentPriceActual(paymentPriceActual)
                .paymentStatus(paymentStatus)
                .build();
    }
}