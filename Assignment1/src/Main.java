import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<User> users = new ArrayList<User>();

        System.out.println("====================");
        System.out.println("회원등록");
        System.out.println("====================");

        boolean register = false;
        Scanner sc = new Scanner(System.in);

        while(!register){
            System.out.println("회원가입을 하시겠습니까?\ny: 진행    n:취소");
            System.out.print(">> ");
            String register_input = sc.nextLine();

            if (register_input.equalsIgnoreCase("y")){
                register = true;
                System.out.println("================");
                System.out.println("회원가입이 진행됩니다.");
                System.out.println("================");
            } else if(register_input.equalsIgnoreCase("n")){
                System.out.println("================");
                System.out.println("회원가입이 종료됩니다.");
                System.out.println("================");
                System.exit(0);
            } else{
                System.out.println("입력 값을 확인해주세요.");
            }

            while(true){
                User new_user = createUser();
                users.add(new_user);

                System.out.println("------------------");
                System.out.println(new_user.getName()+" 님, 가입을 환영합니다.");
                System.out.println("ID는 " + new_user.getUsername()+" 입니다.");

                System.out.println("------------------");

                System.out.println("회원가입을 이어서 하시겠습니까?\ny: 진행    n:취소");
                System.out.print(">> ");
                String register_again = sc.nextLine();

                if (register_again.equalsIgnoreCase("y")){
                    ;
                } else if(register_again.equalsIgnoreCase("n")){
                    System.exit(0);
                }
            }

        }

    }

    static User createUser(){
        Scanner sc = new Scanner(System.in);
        // ID
        System.out.print("ID: ");
        String username = sc.nextLine();

        // PW
        String password = "";
        while(true){
            System.out.print("PW: ");
            password = sc.nextLine();
            System.out.print("PW 확인: ");
            String password_confirm = sc.nextLine();

            if (password.equals(password_confirm)){
                break;
            } else{
                System.out.println("================");
                System.out.println("패스워드가 일치하지 않습니다.");
                System.out.println("패스워드를 다시 입력해주세요.");
                System.out.println("================");
            }
        }

        // 이름
        System.out.print("성명: ");
        String name = sc.nextLine();

        // 생년월일(6자리)
        String birth_date = "";
        while(true){
            System.out.print("생년월일(6자리): ");
            birth_date = sc.nextLine();
            if(birth_date.length() == 6){
                break;
            } else{
                System.out.println("================");
                System.out.println("생년월일 자릿수가 올바르지 않습니다.");
                System.out.println("생년월일을 다시 입력해주세요.");
                System.out.println("================");
            }
        }

        // 이메일
        System.out.print("이메일: ");
        String email = sc.nextLine();

        return new User(username, password, name, birth_date, email);
    }

}
