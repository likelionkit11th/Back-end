package likelion.PostSiteVer1.DTO;
import likelion.PostSiteVer1.Domain.Post;
public record PostDTO(String title, String description) {

    public Post toEntity() {
        return Post.builder()
                .title(title)
                .description(description)
                .build();
    }
}