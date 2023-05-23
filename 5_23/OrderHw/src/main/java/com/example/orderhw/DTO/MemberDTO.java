package com.example.orderhw.DTO;

import com.example.orderhw.Domain.Member;

public record MemberDTO(String name) {
    public Member toEntity(){
        return Member.builder()
                .name(name)
                .build();
    }
}
