package com.example.orderhw.DTO.ToBody;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Getter
@NoArgsConstructor
public class MemberOutDto {

    private Long id;

    private List<Long> orderIds = new ArrayList<>();

    private String name;
    @Builder
    public MemberOutDto(Long id, List<Long> orderIds, String name){
        this.id=id;
        this.orderIds=orderIds;
        this.name=name;
    }
}
