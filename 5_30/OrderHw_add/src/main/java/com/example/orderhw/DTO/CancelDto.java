package com.example.orderhw.DTO;

import com.example.orderhw.Domain.CancelLog;
import com.example.orderhw.Domain.Order;
import com.example.orderhw.Domain.PaymentLog;
import com.example.orderhw.Status.CancelLogStatus;
import com.example.orderhw.Status.PaymentLogStatus;
import com.example.orderhw.Status.PaymentLogTool;

public record CancelDto(Order order, Long cancelPrice, CancelLogStatus cancelLogStatus) {
    public CancelLog toEntity(){
        return CancelLog.builder()
                .order(order)
                .cancelPrice(cancelPrice)
                .cancelLogStatus(cancelLogStatus)
                .build();
    }
}