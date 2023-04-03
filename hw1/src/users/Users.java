package users;
import java.util.ArrayList;

public class Users {
    private ArrayList users;

    public Users() {
        this.users = new ArrayList();
    }

    public boolean addUser(User user) {
        this.users.add(user);
        return true;
    }

    public ArrayList getUsers() {
        return users;
    }

    public void setUsers(ArrayList users) {
        this.users = users;
    }
}
