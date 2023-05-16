package com.mutsa.HW4UserRegisterDatabase.Controller;

import com.mutsa.HW4UserRegisterDatabase.DTO.UserDTO;
import com.mutsa.HW4UserRegisterDatabase.Domain.User;
import com.mutsa.HW4UserRegisterDatabase.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/users")
    public ResponseEntity<Long> joinUser(@RequestBody UserDTO userDTO) {
        Long userId = userService.joinUser(userDTO);
        return ResponseEntity.created(URI.create("/users/" + userId)).body(userId);

    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> findOneUser(@PathVariable Long id) {
        User user = userService.findOneUser(id);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> findAllUsers() {
        List<User> users = userService.findAllUsers();
        return ResponseEntity.ok().body(users);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        userService.updateUser(userDTO, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(value = {NoSuchElementException.class})
    public ResponseEntity<Object> handleNoSuchElementException(NoSuchElementException e) {
        return ResponseEntity.badRequest().body("No such element: " + e.getMessage());
    }
}

