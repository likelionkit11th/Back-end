package mutsa.assignment5.service;

import lombok.RequiredArgsConstructor;
import mutsa.assignment5.dto.AddMemberDto;
import mutsa.assignment5.entity.Member;
import mutsa.assignment5.repository.MemberRepository;
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
