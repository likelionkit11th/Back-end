package likelion.assignment6.service;

import likelion.assignment6.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import likelion.assignment6.dto.AddMemberDto;
import likelion.assignment6.entity.Member;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Optional<Long> createMember(AddMemberDto memberDto){
        Member member = memberDto.toEntity();
        memberRepository.save(member);
        return Optional.ofNullable(member.getMemberId());
    }
}
