package likelion.PostSiteVer1.Repository;

import likelion.PostSiteVer1.Domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findByTitleContaining(String title,Pageable pageable);
    @Query(value = "SELECT * FROM post ORDER BY createdat DESC", nativeQuery = true)
    Page<Post> findAllOrderBycreateddateDesc(Pageable pageable);

}