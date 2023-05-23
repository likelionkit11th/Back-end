package lilelion.assginment4.dto;


import lilelion.assginment4.domain.Post;

public record PostDTO(String title, String content){
    /* 도메인 새딘 DTO를 사용하는 이유
//    *  1. DTO는 계층간 데이터 전송을 위해 도메인 모델 대신 사용 되는 객체
//    *  2. DTO는 순수하게 데이터를 저장하고, 데이터에 대한 getter, setter 만을 가져야함
//    *  3. DTO는 어떠한 비즈니스 로직을 가져서는 안되며, 저장 검색, 직렬화, 역직렬화 로직만을 가져야함
//    *
//    *  4. 도메인 모델을 계층간 전달에 사용하면, UI 계층에서 도메인 모델의 메소드를 호출하거나 상태 변경 가능.
//    *     ㄴ DTO를 사용한다면 도메인 모델을 캡슐화 하여 보호할 수 있음
//    *  5. 모델과 뷰의 결합을 느슨하게 만들어줌
//    *  */

    public Post toEntity(){
        return Post.builder()
                .title(title)
                .content(content)
                .build();
    }

}

