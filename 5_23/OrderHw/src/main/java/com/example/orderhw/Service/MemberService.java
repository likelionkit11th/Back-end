package com.example.orderhw.Service;

import com.example.orderhw.DTO.MemberDTO;
import com.example.orderhw.Domain.Member;
import com.example.orderhw.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional//(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;
    public Long Create(MemberDTO memberDTO){
        Member member = memberDTO.toEntity();
        memberRepository.save(member);
        return member.getId();
    }
}
