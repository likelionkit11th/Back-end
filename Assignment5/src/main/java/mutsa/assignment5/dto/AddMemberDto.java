package mutsa.assignment5.dto;

import mutsa.assignment5.entity.Member;


public record AddMemberDto (String memberName){

    public Member toEntity(){
        return Member.builder()
                .memberName(memberName)
                .build();
    }

}