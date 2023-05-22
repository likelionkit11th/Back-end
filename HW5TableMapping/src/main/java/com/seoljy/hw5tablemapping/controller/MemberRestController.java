package com.seoljy.hw5tablemapping.controller;

import com.seoljy.hw5tablemapping.dto.MemberDTO;
import com.seoljy.hw5tablemapping.service.MemberService;
import com.seoljy.hw5tablemapping.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.net.URI;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberRestController {
    private final MemberService memberService;

    @PostMapping("/member")
    public ResponseEntity<Void> creatMember(@RequestBody MemberDTO memberDTO) {
        memberService.createMember(memberDTO);
        log.info(memberDTO.toString());
        log.info("[CREATE]");
        return ResponseEntity.created(URI.create("/member")).build();
    }
}
