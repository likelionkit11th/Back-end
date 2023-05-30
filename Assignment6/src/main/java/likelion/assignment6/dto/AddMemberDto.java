package likelion.assignment6.dto;

import likelion.assignment6.entity.Member;


public record AddMemberDto (String memberName){

    public Member toEntity(){
        return Member.builder()
                .memberName(memberName)
                .build();
    }

}