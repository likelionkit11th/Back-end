import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private String username;
    private String password;
    private String name;
    private String birth_date;
    private String email;

    public User(String username,  String password, String name, String birth_date, String email) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.birth_date = birth_date;
        this.email = email;
    }

}
