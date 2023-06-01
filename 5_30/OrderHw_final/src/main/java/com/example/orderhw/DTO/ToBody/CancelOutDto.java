package com.example.orderhw.DTO.ToBody;

import com.example.orderhw.Domain.Order;
import com.example.orderhw.Status.CancelLogStatus;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
@Getter
@NoArgsConstructor
public class CancelOutDto {

    private Long id;

    private Long orderId;

    private Long cancelPrice;

    private CancelLogStatus cancelstatus;

    private LocalDateTime cancelTime;

    @Builder
    public CancelOutDto(Long id, Long orderId, Long cancelPrice, CancelLogStatus cancelLogStatus, LocalDateTime cancelTime){
        this.id=id;
        this.orderId=orderId;
        this.cancelPrice=cancelPrice;
        this.cancelstatus=cancelLogStatus;
        this.cancelTime=cancelTime;
    }

}
