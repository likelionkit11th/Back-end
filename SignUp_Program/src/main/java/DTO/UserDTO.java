package DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class UserDTO {
    private String id;
    private String password;
    private String name;
    private String birth_date;
    private String email;

    public UserDTO(String id, String password, String name, String birth_date, String email)
    {
        this.id = id;
        this.password = password;
        this.name = name;
        this.birth_date = birth_date;
        this.email = email;
    }
}

