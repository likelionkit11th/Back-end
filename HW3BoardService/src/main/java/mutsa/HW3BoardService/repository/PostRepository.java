package mutsa.HW3BoardService.repository;

import mutsa.HW3BoardService.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface   PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByTitleContainingOrderByCreatedAtDesc(String title);
    Page<Post> findAll(Pageable pageable);
}
