package likelion.HW2UserRegisterSpring.Domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private Long id;
    private String userName;
    private String password;
}
