package com.mutsa.HW4UserRegisterDatabase.Service;

import com.mutsa.HW4UserRegisterDatabase.DTO.UserDTO;
import com.mutsa.HW4UserRegisterDatabase.Repository.UserRepository;
import com.mutsa.HW4UserRegisterDatabase.Domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;

    //회원 가입
    @Transactional
    public Long joinUser(UserDTO userDTO) {
        User user = userDTO.toEntity();
        userRepository.save(user);
        return user.getId();
    }

    //회원  단건 조회
    public User findOneUser(Long id) {
        return userRepository
                .findById(id)
                .orElseThrow(()-> new NoSuchElementException("id"+ id + "에 해당하는 사용자가 없습니다."));
    }

    //회원 전체 조회
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    //회원 수정
    @Transactional
    public void updateUser(UserDTO userDTO, Long id) {
        userRepository
                .findById(id)
                .orElseThrow(()-> new NoSuchElementException("id"+ id + "에 해당하는 사용자가 없습니다."))
                .update(userDTO.username(), userDTO.password());
    }

    //회원 삭제
    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
