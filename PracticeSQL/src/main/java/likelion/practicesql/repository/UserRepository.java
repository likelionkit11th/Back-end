package likelion.practicesql.repository;

import likelion.practicesql.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}