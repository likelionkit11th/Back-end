package Repository;

import Domain.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;


class MemoryRepositoryTest {
    MemoryRepository memoryRepository = new MemoryRepository();
    @Test
    void save() {
        User user = new User();
        user.setUserName("안현진");
        user.setPassword("1234");

        memoryRepository.save(user);

        User findUser = memoryRepository.findById(user.getId());

        Assertions.assertThat(user).isEqualTo(findUser);
    }
}