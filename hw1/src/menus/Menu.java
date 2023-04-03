package menus;
import users.User;
import users.Users;
import utils.Utils;

import java.util.Scanner;

public class Menu {
    public static void showMainMenu(Scanner sc) {
        System.out.println("=========================");
        System.out.println("회원등록");
        System.out.println("=========================");

        System.out.println("회원가입을 하시겠습니까?\ny: 진행     N: 취소");

        if(Utils.optionSelector(sc)) {
            System.out.println("=========================");
            System.out.println("회원가입이 진행됩니다.");
            System.out.println("=========================");
        } else {
            System.out.println("=========================");
            System.out.println("회원가입이 종료됩니다.");
            System.out.println("=========================");
            System.exit(0);
        }
    }

    public static User showRegisterMenu(Scanner sc, Users users) {
        // ID
        System.out.print("ID: ");
        String username = sc.nextLine();

        // PW
        String password = "";
        while (true) {
            System.out.print("PW: ");
            password = sc.nextLine();
            System.out.print("PW 확인: ");
            String password_confirm = sc.nextLine();

            if (password.equals(password_confirm)) {
                break;
            } else {
                System.out.println("=========================");
                System.out.println("패스워드가 일치하지 않습니다.");
                System.out.println("패스워드를 다시 입력해주세요.");
                System.out.println("=========================");
            }
        }

        // 이름
        System.out.print("성명: ");
        String name = sc.nextLine();

        // 생년월일(6자리)
        String birth_date = "";
        while (true) {
            System.out.print("생년월일(6자리): ");
            birth_date = sc.nextLine();
            if (birth_date.length() == 6) {
                break;
            } else {
                System.out.println("=========================");
                System.out.println("생년월일 자릿수가 올바르지 않습니다.");
                System.out.println("생년월일을 다시 입력해주세요.");
                System.out.println("=========================");
            }
        }

        // 이메일
        System.out.print("이메일: ");
        String email = sc.nextLine();

        User user = new User(username, password, name, birth_date, email);
        users.addUser(user);
        return user;
    }

    public static void showGreetingMenu(User user) {
        System.out.println("=========================");
        System.out.println(user.getName() + "님, 가입을 환영합니다.");
        System.out.println("ID는 " + user.getUsername() + "입니다.\n");

        System.out.print(user);
        System.out.println("=========================");
    }

    public static void showNewRegisterMenu(Scanner sc) {
        System.out.println("\n회원가입을 이어서 진행 하시겠습니까?\ny: 진행     N: 취소");
        if(!Utils.optionSelector(sc)) {
            System.exit(0);
        }
    }
}
