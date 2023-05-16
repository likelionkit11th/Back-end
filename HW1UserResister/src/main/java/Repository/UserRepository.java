package Repository;

import Domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User save(User user);
    User findById(Long id);
    Optional<User> findByUserName(String userName);
    List<User> findAll();
}
