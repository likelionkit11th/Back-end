package lilelion.assginment4.repository;

import lilelion.assginment4.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataJpaPostRepository extends JpaRepository<Post, Long>, PostRepository {


}
