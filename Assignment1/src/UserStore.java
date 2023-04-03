import java.util.ArrayList;

public class UserStore {
    private ArrayList<User> users;

    public UserStore() {
        users = new ArrayList<>();
    }

    public void addUser(User user){
        users.add(user);
    }
}
