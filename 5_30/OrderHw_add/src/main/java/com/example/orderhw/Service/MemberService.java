package com.example.orderhw.Service;

import com.example.orderhw.DTO.MemberDto;
import com.example.orderhw.Domain.CancelLog;
import com.example.orderhw.Domain.Member;
import com.example.orderhw.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;
    public Long addMember(MemberDto memberDto) {
        Member member = memberDto.toEntity();
        memberRepository.save(member);
        return member.getId();
    }

    public void editMember(MemberDto memberDto,Long memberId) throws Exception {
        memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException())
                .update(memberDto.name());
    }

    public void deleteMember(Long memberId){
        memberRepository.deleteById(memberId);
    }

    public Member findOneMember(Long memberId) throws Exception{
        return memberRepository
                .findById(memberId)
                .orElseThrow(()-> new RuntimeException());
    }

    public List<Member> getMemberPage(Pageable pageable) {
        Page<Member> all = memberRepository.findAll(pageable);
        List<Member> memberPage = all.getContent();
        return memberPage;
    }
}
