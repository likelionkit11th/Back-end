package com.example.orderhw.DTO.Create;

import com.example.orderhw.Domain.Order;
import com.example.orderhw.Domain.OrderProduct;
import com.example.orderhw.Domain.PaymentLog;
import com.example.orderhw.Status.OrderProductStatus;
import com.example.orderhw.Status.PaymentLogStatus;
import com.example.orderhw.Status.PaymentLogTool;

public record PaymentDto(Long orderId, PaymentLogTool paymentTool, Long paymentPriceActual) {
    public PaymentLog toEntity(Order order){
        return PaymentLog.builder()
                .order(order)
                .paymentTool(paymentTool)
                .paymentPriceActual(paymentPriceActual)
                .build();
    }
}