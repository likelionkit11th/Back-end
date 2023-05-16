package likelion.HW2UserRegisterSpring.Controller;

import likelion.HW2UserRegisterSpring.Domain.User;
import likelion.HW2UserRegisterSpring.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/v1")
public class UserThymeleafController {
    private final UserService userService;

    @Autowired
    public UserThymeleafController(UserService userService) {
        this.userService = userService;
    }

    //회원 가입 폼 조회
    @GetMapping("/new")
    public String createForm() {
        return "createUserForm";
    }

    //회원 가입
    @PostMapping("/new")
    public String create(UserDTO userDTO) {
        User user = new User();
        user.setUserName(userDTO.getUserName());
        user.setPassword(userDTO.getPassword());
        log.info("userName = {} , password = {}", user.getUserName(), user.getPassword());

        userService.join(user);
        return "redirect:/v1";
    }

    //회원 리스트 조회
    @GetMapping()
    public String userList(Model model) {
        List<User> users = userService.findUsers();
        model.addAttribute("users", users);
        return "userList";
    }

    //회원  단건 조회
    @GetMapping("/{userId}")
    public String userDetail(@PathVariable("userId") String userId, Model model) {
        User user = userService.findUser(Long.valueOf(userId));
        model.addAttribute("user", user);
        return "userDetail";
    }


    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<String> handleDuplicate(IllegalStateException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }
}
