package com.example.orderhw.DTO.ToBody;

import com.example.orderhw.Status.OrderProductStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderProductOutDto {

    private Long id;

    private Long orderId;

    private Long CancelLogId;

    private String productName;

    private Long productPrice;

    private Long productCount;

    private OrderProductStatus productStatus;

    @Builder
    private OrderProductOutDto(Long id, Long orderId, Long cancelLogId, String productName, Long productPrice, Long productCount, OrderProductStatus productStatus){
        this.id=id;
        this.orderId=orderId;
        this.CancelLogId=cancelLogId;
        this.productName=productName;
        this.productPrice=productPrice;
        this.productCount=productCount;
        this.productStatus=productStatus;
    }


}
