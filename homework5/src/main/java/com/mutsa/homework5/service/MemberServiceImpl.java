package com.mutsa.homework5.service;

import com.mutsa.homework5.domain.Member;
import com.mutsa.homework5.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    @Autowired
    private final MemberRepository memberRepository;

    @Override
    public Member save(Member member) {
        try {
            return memberRepository.save(
                    new Member(member.getName())
            );
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Optional<Member> findById(Long id) {
        try {
            Optional<Member> member = memberRepository.findById(id);
            if(member.isPresent()){
                return member;
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
