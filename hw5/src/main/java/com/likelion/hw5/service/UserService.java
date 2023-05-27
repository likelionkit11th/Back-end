package com.likelion.hw5.service;


import com.likelion.hw5.domain.User;
import com.likelion.hw5.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;


    public void join(UserDto userDto){
        User user = new User(userDto.getName());
        userRepository.save(user);
    }



    @Data
    @AllArgsConstructor
    public static class UserDto{
        private String name;
    }
}
