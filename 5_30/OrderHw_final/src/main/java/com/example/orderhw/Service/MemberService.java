package com.example.orderhw.Service;

import com.example.orderhw.DTO.Create.MemberDto;
import com.example.orderhw.DTO.ToBody.MemberOutDto;
import com.example.orderhw.Domain.Member;
import com.example.orderhw.Repository.MemberRepository;
import com.example.orderhw.Repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;
    public Long addMember(MemberDto memberDto) {
        Member member = memberDto.toEntity();
        member.setAddOrder();
//        member.setOrder(null);
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

    public MemberOutDto findOneMember(Long memberId) throws Exception{
        Member member = memberRepository
                .findById(memberId)
                .orElseThrow(()-> new RuntimeException());
        MemberOutDto memberOutDto = MemberOutDto.builder()
                .id(member.getId())
                .orderIds(member.getOrderId())
                .name(member.getName())
                .build();
        return memberOutDto;
    }

    public List<MemberOutDto> getMemberPage(Pageable pageable) {
        pageable= PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.DESC,"id"));
        List<Member> all = memberRepository.findAll();
        List<MemberOutDto> memberOutDtoPage= new ArrayList<>();
        for(Member member : all){
            MemberOutDto memberOutDto = MemberOutDto.builder()
                    .id(member.getId())
                    .orderIds(member.getOrderId())
                    .name(member.getName())
                    .build();
            memberOutDtoPage.add(memberOutDto);
        }
        Page<MemberOutDto> memberAll= new PageImpl<>(memberOutDtoPage,pageable,memberOutDtoPage.size());
        List<MemberOutDto> memberPage = memberAll.getContent();
        return memberPage;
    }
}
