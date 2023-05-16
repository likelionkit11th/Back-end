package likelion.HW2UserRegisterSpring.Controller;

import likelion.HW2UserRegisterSpring.Domain.User;
import likelion.HW2UserRegisterSpring.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v2")
@Slf4j
public class UserRestController {
    private final UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User createUser(@RequestBody UserDTO userDTO) {
        User user = new User();
        user.setUserName(userDTO.getUserName());
        user.setPassword(userDTO.getPassword());
        log.info("userName = {}, password={}", user.getUserName(), user.getPassword());

        userService.join(user);
        return user;
    }

    @GetMapping
    public List<User> userList() {
        return userService.findUsers();
    }

    @GetMapping("/{userId}")
    public User userDetail(@PathVariable String userId) {
        return userService.findUser(Long.valueOf(userId));
    }
}
