package com.HW6OrderService.DTO;

import com.HW6OrderService.Domain.Member;

public record MemberDTO(String name) {
    public Member toEntity() {
        return new Member(name);
    }
}