package com.example.orderhw.DTO.ToBody;

import com.example.orderhw.Domain.Order;
import com.example.orderhw.Status.OrderStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Getter
@NoArgsConstructor
public class OrderOutDto {

    private Long id;

    private Long memberId;

    private String orderMemberName;

    private Long orderPrice;

    private OrderStatus orderStatus;

    private LocalDateTime orderTime;
    private List<OrderProductOutDto> orderProducts = new ArrayList<>();
    @Builder
    public OrderOutDto(Long id, Long memberId, String orderMemberName, Long orderPrice, OrderStatus orderStatus, LocalDateTime orderTime, List<OrderProductOutDto> orderProducts){
        this.id=id;
        this.memberId=memberId;
        this.orderMemberName=orderMemberName;
        this.orderPrice=orderPrice;
        this.orderStatus=orderStatus;
        this.orderTime=orderTime;
        this.orderProducts=orderProducts;
    }
}
