package com.seoljy.hw5tablemapping.dto;

import com.seoljy.hw5tablemapping.domain.Member;

public record MemberDTO(String name) {
    public Member toEntity() {
        return Member.builder().name(name).build();
    }
}