package likelion.PostSiteVer1.Repository;

import likelion.PostSiteVer1.Domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
//    @Query(value = "SELECT * FROM post ORDER BY created_at DESC", nativeQuery = true)
//    List<Post> findByTitleContaining(String title);
    Page<Post> findByTitleContaining(String title,Pageable pageable);
//    @Query(value = "SELECT * FROM post ORDER BY created_at DESC", nativeQuery = true)
//    List<Post> findAllOrderBycreatedAtDesc();
    @Query(value = "SELECT * FROM post ORDER BY created_at DESC", nativeQuery = true)
    Page<Post> findAllOrderBycreatedAtDesc(Pageable pageable);
}