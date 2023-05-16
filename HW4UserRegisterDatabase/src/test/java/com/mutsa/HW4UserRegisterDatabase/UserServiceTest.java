package com.mutsa.HW4UserRegisterDatabase;

import com.mutsa.HW4UserRegisterDatabase.DTO.UserDTO;
import com.mutsa.HW4UserRegisterDatabase.Domain.User;
import com.mutsa.HW4UserRegisterDatabase.Service.UserService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Transactional
public class UserServiceTest {

	@Autowired
	UserService userService;

	@Test
	@DisplayName("회원가입 테스트")
	void joinUserTest() {
		// given
		UserDTO userDTO = new UserDTO("testUser", "testPassword");

		// when
		Long savedUserId = userService.joinUser(userDTO);
		User savedUser = userService.findOneUser(savedUserId);

		// then
		assertThat(savedUser.getUsername()).isEqualTo(userDTO.username());
		assertThat(savedUser.getPassword()).isEqualTo(userDTO.password());
	}

	@Test
	@DisplayName("회원 조회 테스트")
	void findOneUserTest() {
		// given
		UserDTO userDTO = new UserDTO("testUser", "testPassword");
		Long savedUserId = userService.joinUser(userDTO);

		// when
		User foundUser = userService.findOneUser(savedUserId);

		// then
		assertThat(foundUser.getUsername()).isEqualTo(userDTO.username());
		assertThat(foundUser.getPassword()).isEqualTo(userDTO.password());
	}

	@Test
	@DisplayName("전체 회원 조회 테스트")
	void findAllUsersTest() {
		// given
		UserDTO userDTO1 = new UserDTO("testUser1", "testPassword1");
		UserDTO userDTO2 = new UserDTO("testUser2", "testPassword2");
		userService.joinUser(userDTO1);
		userService.joinUser(userDTO2);

		// when
		List<User> allUsers = userService.findAllUsers();

		// then
		assertThat(allUsers.size()).isEqualTo(2);
	}

	@Test
	@DisplayName("회원 수정 테스트")
	void updateUserTest() {
		// given
		UserDTO userDTO = new UserDTO("testUser", "testPassword");
		Long savedUserId = userService.joinUser(userDTO);
		String updatedUsername = "updatedUsername";
		String updatedPassword = "updatedPassword";
		UserDTO updatedUserDTO = new UserDTO(updatedUsername, updatedPassword);

		// when
		userService.updateUser(updatedUserDTO, savedUserId);
		User updatedUser = userService.findOneUser(savedUserId);

		// then
		assertThat(updatedUser.getUsername()).isEqualTo(updatedUsername);
		assertThat(updatedUser.getPassword()).isEqualTo(updatedPassword);
	}

	@Test
	@DisplayName("회원 삭제 테스트")
	void deleteUserTest() {
		// given
		UserDTO userDTO = new UserDTO("testUser", "testPassword");
		Long savedUserId = userService.joinUser(userDTO);

		// when
		userService.deleteUser(savedUserId);
		List<User> allUsers = userService.findAllUsers();

		// then
		assertThat(allUsers.size()).isEqualTo(0);
	}
}