package likelion.HW2UserRegisterSpring.Service;

import likelion.HW2UserRegisterSpring.Domain.User;
import likelion.HW2UserRegisterSpring.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private  final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Long join(User user) {
        validateDuplicateUserName(user);
        userRepository.save(user);

        return user.getId();
    }

    //중복 가입 방지
    private void validateDuplicateUserName(User user) {
        userRepository.findByUserName(user.getUserName())
                .ifPresent( m -> {
                    throw new IllegalStateException("이미 존재하는 이름입니다.");
                });
    }

    public User findUser(Long userId) {
        return userRepository.findById(userId);
    }

    public List<User> findUsers() {
        return userRepository.findAll();
    }
}
