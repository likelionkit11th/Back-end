package Service;

import Domain.User;
import Repository.MemoryRepository;
import org.junit.jupiter.api.Test;

class UserServiceTest {
    MemoryRepository memoryRepository = new MemoryRepository();
    UserService userService = new UserService(memoryRepository);

    @Test
    void join() {
        User user1 = new User();
        user1.setUserName("안현진");
        user1.setPassword("1234");
        userService.join(user1);

        User user2 = new User();
        user2.setUserName("안현진");
        user2.setPassword("1234");
        userService.join(user2);
    }
}