package com.example.orderhw.Controller;

import com.example.orderhw.DTO.MemberDTO;
import com.example.orderhw.Service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/member")
    public ResponseEntity<Long> orderCreate(@RequestBody MemberDTO memberDTO) {
        Long memberId = memberService.Create(memberDTO);
        return ResponseEntity.created(URI.create("/member/" + memberId)).body(memberId);
    }
}
