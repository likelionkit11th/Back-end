package com.likelion.hw1;


import java.util.ArrayList;
import java.util.Scanner;

public class UserRegister {

    enum USER_RESPONSE{
        YES, NO, UNAVAILABLE_VALUE;
    }

    public static void main(String[] args) {
        System.out.println("====================");
        System.out.println("회원 등록");
        System.out.println("====================");


        ArrayList<User> users = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        while(true){
            USER_RESPONSE registerInput = askDoesUserWantsRegister(sc, users.size() == 0);

            if(registerInput == USER_RESPONSE.YES){
                System.out.println("====================");
                System.out.println("회원가입이 진행됩니다.");
                System.out.println("====================");
            }else if(registerInput == USER_RESPONSE.NO) {
                System.out.println("====================");
                System.out.println("회원가입이 종료됩니다.");
                System.out.println("====================");
                System.exit(0);
            }else{
                System.out.println("입력값을 확인해주세요");
                continue;
            }


            User newUser = createUser(sc);
            users.add(newUser);

            System.out.println("====================");
            System.out.printf("%s님, 가입을 환영합니다.\n", newUser.getName());
            System.out.printf("ID는 %s 입니다.\n", newUser.getUsername());

            }

        }

    private static User createUser(Scanner sc) {
        System.out.print("ID :");
        String username = sc.nextLine();

        String password = "";
        while(true){
            System.out.print("password : ");
            password = sc.nextLine();
            System.out.print("password 확인 : ");
            String passwordConfirm = sc.nextLine();

            if(password.equals(passwordConfirm)) break;

            System.out.println("====================");
            System.out.println("패스워드가 일치하지 않습니다. 다시 입력해 주세요.");
            System.out.println("====================");
        }

        // 이름
        System.out.print("성명 : ");
        String name = sc.nextLine();

        //생년월일(6자리)
        String birthDate = "";
        while(true){
            System.out.println("생년월일(6자리)");
            birthDate = sc.nextLine();
            if(birthDate.length() == 6) break;

            System.out.println("====================");
            System.out.println("생년월일 자릿수가 올바르지 않습니다.");
            System.out.println("생년월일을 다시 입력해 주세요");
            System.out.println("====================");
        }

        System.out.print("이메일 : ");
        String email = sc.nextLine();

        User newUser = User.builder().username(username)
                .password(password)
                .name(name)
                .birthDate(birthDate)
                .email(email).build();
        return newUser;
    }

    private static USER_RESPONSE askDoesUserWantsRegister(Scanner sc, boolean isFirst) {
        if(isFirst) System.out.println("회원가입을 하시겠습니까?\ny: 진행   n:취소");
        else System.out.println("회원가입을 계속 진행하시겠습니까?\ny: 진행   n:취소");

        System.out.print(">> ");

        String registerInput = sc.nextLine();

        if(registerInput.equalsIgnoreCase("y")) return USER_RESPONSE.YES;
        else if(registerInput.equalsIgnoreCase("n")) return USER_RESPONSE.NO;
        else return USER_RESPONSE.UNAVAILABLE_VALUE;
    }
}
