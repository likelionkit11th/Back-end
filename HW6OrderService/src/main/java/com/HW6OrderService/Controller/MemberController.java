package com.HW6OrderService.Controller;
import com.HW6OrderService.DTO.MemberDTO;
import com.HW6OrderService.Service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/members")
    public void createMember(@RequestBody MemberDTO memberDTO) {
        memberService.createMember(memberDTO);
    }
}