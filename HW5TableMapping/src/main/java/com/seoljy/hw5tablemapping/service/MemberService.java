package com.seoljy.hw5tablemapping.service;

import com.seoljy.hw5tablemapping.domain.Member;
import com.seoljy.hw5tablemapping.dto.MemberDTO;
import com.seoljy.hw5tablemapping.repository.DataJpaMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class MemberService {
    private final DataJpaMemberRepository dataJpaMemberRepository;

    public void createMember(MemberDTO memberDTO) {
        Member member = memberDTO.toEntity();
        dataJpaMemberRepository.save(member);
    }
}
