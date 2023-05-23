package mutsa.assignment5.controller;

import lombok.RequiredArgsConstructor;
import mutsa.assignment5.dto.AddMemberDto;
import mutsa.assignment5.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;


    @PostMapping("/member")
    public ResponseEntity<Long> createMember(@RequestBody AddMemberDto memberDto){
        Long memberId = memberService.createMember(memberDto).get();
        return ResponseEntity.ok().body(memberId);

    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleException(Exception e) {
        return ResponseEntity.badRequest().body("Exception: " + e.getMessage());
    }



}
