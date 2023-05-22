package com.mutsa.homework5.service;

import com.mutsa.homework5.domain.Member;
import com.mutsa.homework5.domain.OrderProduct;

import java.util.Optional;

public interface MemberService {
    public Member save(Member member);
    public Optional<Member> findById(Long id);
}
