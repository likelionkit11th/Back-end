package Service;

import Domain.User;
import Repository.UserRepository;

import java.util.List;

public class UserService {
    private  final UserRepository userRepository;

    //의존성 주입
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Long join(User user) {
        validateDuplicateUserName(user);
        userRepository.save(user);

        return user.getId();
    }

    private void validateDuplicateUserName(User user) {
        userRepository.findByUserName(user.getUserName())
                .ifPresent( m -> {
                    throw new IllegalStateException();
                });
    }

    public List<User> findUsers() {
        return userRepository.findAll();
    }
}
