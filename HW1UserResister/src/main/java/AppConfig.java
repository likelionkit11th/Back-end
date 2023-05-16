import Repository.MemoryRepository;
import Service.UserService;
import View.Console;

public class AppConfig {
    public AppConfig(){
        MemoryRepository memoryRepository = new MemoryRepository();
        UserService userService = new UserService(memoryRepository);
        new Console(userService);
    }
}
