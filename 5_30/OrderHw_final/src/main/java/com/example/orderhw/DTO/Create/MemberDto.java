package com.example.orderhw.DTO.Create;

import com.example.orderhw.Domain.Member;

public record MemberDto(String name) {
    public Member toEntity(){
        return Member.builder()
                .name(name)
                .build();
    }
}
