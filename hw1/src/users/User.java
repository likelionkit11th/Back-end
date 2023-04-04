package users;

public class User {
    private String username, password, name, birth_date, email;

    public User(String username, String password, String name, String birth_date, String email) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.birth_date = birth_date;
        this.email = email;
    }
    public String toString() {
        String message = "";

        message += "ID: " + this.username + "\n";
        message += "PW: " + this.password + "\n";
        message += "성명: " + this.name + "\n";
        message += "생년월일: " + this.birth_date + "\n";
        message += "이메일: " + this.email + "\n";

        return message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
