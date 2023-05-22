package com.mutsa.homework5.controller;

import com.mutsa.homework5.domain.Member;
import com.mutsa.homework5.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class MemberController {

    @Autowired
    private final MemberService memberService;

    @GetMapping("/members/{id}")
    public ResponseEntity<Optional<Member>> getOrderProductById(@PathVariable("id") long id) {
        try {
            return ResponseEntity.ok(memberService.findById(id));
        } catch(Exception e){
            e.printStackTrace();
        }
        return null;

    }

    @PostMapping("/members")
    public ResponseEntity<Member> createMember(@RequestBody Member member) {
        try {
            ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(memberService.save(member));
        } catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
