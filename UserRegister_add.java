import java.util.Scanner;
import java.util.StringTokenizer;
class userinfo{
    String id;
    String password;
    String name;
    String birth_date;
    String email;
    public userinfo(String ID, String PW, String Name, String Birth, String Email ){
        this.id = ID;
        this.password = PW;
        this.name = Name;
        this.birth_date = Birth;
        this.email = Email;
    }
    public void infoprint(int i){
        System.out.println("=======================");
        System.out.println(i+" 번째 등록자 님의 ID는 "+this.id+"이고 비밀번호는 "+this.password+"입니다");
        System.out.println(i+" 번 등록자 님의 이름은 "+this.name+"이고 생일은 "+this.birth_date+"입니다");
        System.out.println(i+" 번째 등록자 님의 이메일 주소는"+this.email+" 입니다");
        System.out.println("=======================");
    }
}
public class UserRegister_add {
    Scanner sc = new Scanner(System.in);
    boolean checking(){
        System.out.println("회원가입을 하시겠습니까?\ny:진행    N:취소");
        System.out.print(">>");
        String register_input = sc.nextLine();
        if (register_input.equalsIgnoreCase("y")) {
            System.out.println("=======================");
            System.out.println("회원가입이 진행됩니다.");
            System.out.println("=======================");
            return true;

        } else if (register_input.equalsIgnoreCase("n")) {
            System.out.println("=======================");
            System.out.println("회원가입이 종료됩니다.");
            System.out.println("=======================");
            System.exit(0);
        } else {
            System.out.println("입력 값을 확인해주세요");
            System.out.println("=======================");
        }
        return false;

    }
    public static void main(String[] args){
        UserRegister_add check = new UserRegister_add();
        userinfo[] info = new userinfo[20000];
        System.out.println("=======================");
        System.out.println("회원등록");
        System.out.println("=======================");
        Scanner sc = new Scanner(System.in);

        boolean register = false;
        while (!register) {
            System.out.println("회원가입을 하시겠습니까?\ny:진행    N:취소");
            System.out.print(">>");
            String register_input = sc.nextLine();
            if (register_input.equalsIgnoreCase("y")) {
                register = true;
                System.out.println("=======================");
                System.out.println("회원가입이 진행됩니다.");
                System.out.println("=======================");

            } else if (register_input.equalsIgnoreCase("n")) {
                System.out.println("=======================");
                System.out.println("회원가입이 종료됩니다.");
                System.out.println("=======================");
                System.exit(0);

            } else {
                System.out.println("입력 값을 확인해주세요");
            }
        }
        int i=1;
        while(true){
            //id
            System.out.print("ID: ");
            String username=sc.nextLine();

            //pw
            String password="";
            while(true){
                System.out.print("PW: ");
                password=sc.nextLine();
                System.out.print("PW 확인: ");
                String password_confirm=sc.nextLine();
                if (password.equals(password_confirm)){
                    break;
                }else{
                    System.out.println("=======================");
                    System.out.println("패스워드가 일치하지 않습니다.");
                    System.out.println("패스워드를 다시 입력해주세요.");
                    System.out.println("=======================");
                }
            }

            //이름
            System.out.print("성명: ");
            String name = sc.nextLine();

            //생년월일(6자리)
            String birth_date="";
            while(true){
                System.out.print("생생년월일(6자리): ");
                birth_date = sc.nextLine();
                if(birth_date.length()==6){
                    break;
                }else{
                    System.out.println("=======================");
                    System.out.println("생년월일 자릿수가 올바르지 않습니다.");
                    System.out.println("생년월일을 다시 입력해주세요.");
                    System.out.println("=======================");
                }
            }

            //이메일
            System.out.print("이메일: ");
            String email = sc.nextLine();

            info[i] = new userinfo(username,password,name,birth_date,email);
            System.out.println("-----------------------");
            System.out.println(info[i].name+"님, 가입을 환영합니다.");
            System.out.println("ID는 "+info[i].id+" 입니다");
            System.out.println("-----------------------");
            i++;
            register=false;
            while(!register){
                System.out.println(i+" 째째 회원가입을 새로 하시겠습니까?\ny:진행    N:취소     I:회원가입 정보 출력");
                System.out.print(">>");
                String register_again = sc.nextLine();
                if (register_again.equalsIgnoreCase("y")) {
                    register=true;
                } else if (register_again.equalsIgnoreCase("n")) {
                    System.exit(0);
                } else if (register_again.equalsIgnoreCase("i")) {
                    boolean register2 = false;
                    while (!register2) {
                        String cnt1;
                        int cnt2;
                        System.out.print("출력하길 원하는 회원가입 정보의 번호를 입력해주세요");
                        cnt1 = sc.nextLine();
                        cnt2 = Integer.parseInt(cnt1);
                        if (cnt2 < i) {
                            info[cnt2].infoprint(cnt2);
                            register2 = true;
                        } else {
                            System.out.println("잘못된 값을 입력하였습니다.");
                            System.out.println("=======================");
                        }
                    }
                }else{
                    System.out.println("잘못된 값을 입력하였습니다.");
                    System.out.println("=======================");
                }
            }
        }
    }
}

