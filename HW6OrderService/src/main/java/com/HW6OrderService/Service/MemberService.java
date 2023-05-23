package com.HW6OrderService.Service;

import com.HW6OrderService.DTO.MemberDTO;
import com.HW6OrderService.Domain.Member;
import com.HW6OrderService.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    public void createMember(MemberDTO memberDTO) {
        Member member = memberDTO.toEntity();
        memberRepository.save(member);
    }
}
