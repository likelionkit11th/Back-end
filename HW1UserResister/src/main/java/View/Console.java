package View;

import Domain.User;
import Service.UserService;

import java.util.Scanner;

public class Console {

    private final UserService userService;
    String choice;
    Boolean flag;

    public Console(UserService userService) {
        this.userService = userService;
        loopPrint();
    }

    Scanner scanner = new Scanner(System.in);

    public void loopPrint(){
        while (true) {
            System.out.println("==============================");
            System.out.println("회원가입 : 1\n회원조회  : 2\n종료 : 3");
            System.out.print(">> ");
            choice = scanner.nextLine();
            System.out.println("==============================");

            if (choice.equalsIgnoreCase("1")) {
                System.out.println("회원가입을 진행합니다.");
                printRegister(userService, scanner);
            } else if(choice.equalsIgnoreCase("2")){
                printUserList(userService);
            } else if (choice.equals("3")) {
                System.exit(0);
            }else{
                System.out.println("올바른 입력을 하십시오");
            }
    }
}

    private  void printUserList(UserService userService) {
        System.out.println("가입된 회원은 다음과 같습니다.");
        userService.findUsers().stream().
                forEach(
                        s -> System.out.println("이름 : " + s.getUserName())
                );
    }

    private  void printRegister(UserService userService, Scanner scanner) {
        do{
            User user = new User();
            System.out.print("ID : ");
            user.setUserName(scanner.nextLine());

            System.out.print("PW : ");
            user.setPassword(scanner.nextLine());

            flag = avoidDuplivate(userService, user);
        }while(flag);
    }

    private  boolean avoidDuplivate(UserService userService, User user) {
        try  {
            userService.join(user);
            flag=false;
            System.out.println("회원가입이 완료되었습니다.");
        } catch (IllegalStateException e) {
            System.out.println("이미 존재하는 회원입니다.");
            flag = true;
        }
        return flag;
    }
}
