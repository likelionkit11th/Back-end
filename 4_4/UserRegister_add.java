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
        System.out.println(i+" ��° ����� ���� ID�� "+this.id+"�̰� ��й�ȣ�� "+this.password+"�Դϴ�");
        System.out.println(i+" ��° ����� ���� �̸��� "+this.name+"�̰� ������ "+this.birth_date+"�Դϴ�");
        System.out.println(i+" ��° ����� ���� �̸��� �ּҴ�"+this.email+" �Դϴ�");
        System.out.println("=======================");
    }
}
public class UserRegister_add {
    Scanner sc = new Scanner(System.in);
    boolean checking(){
        System.out.println("ȸ�������� �Ͻðڽ��ϱ�?\ny:����    N:���");
        System.out.print(">>");
        String register_input = sc.nextLine();
        if (register_input.equalsIgnoreCase("y")) {
            System.out.println("=======================");
            System.out.println("ȸ�������� ����˴ϴ�.");
            System.out.println("=======================");
            return true;

        } else if (register_input.equalsIgnoreCase("n")) {
            System.out.println("=======================");
            System.out.println("ȸ�������� ����˴ϴ�.");
            System.out.println("=======================");
            System.exit(0);
        } else {
            System.out.println("�Է� ���� Ȯ�����ּ���");
            System.out.println("=======================");
        }
        return false;

    }
    public static void main(String[] args){
        UserRegister_add check = new UserRegister_add();
        userinfo[] info = new userinfo[20000];
        System.out.println("=======================");
        System.out.println("ȸ�����");
        System.out.println("=======================");
        Scanner sc = new Scanner(System.in);

        boolean register = false;
        while (!register) {
            System.out.println("ȸ�������� �Ͻðڽ��ϱ�?\ny:����    N:���");
            System.out.print(">>");
            String register_input = sc.nextLine();
            if (register_input.equalsIgnoreCase("y")) {
                register = true;
                System.out.println("=======================");
                System.out.println("ȸ�������� ����˴ϴ�.");
                System.out.println("=======================");

            } else if (register_input.equalsIgnoreCase("n")) {
                System.out.println("=======================");
                System.out.println("ȸ�������� ����˴ϴ�.");
                System.out.println("=======================");
                System.exit(0);

            } else {
                System.out.println("�Է� ���� Ȯ�����ּ���");
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
                System.out.print("PW Ȯ��: ");
                String password_confirm=sc.nextLine();
                if (password.equals(password_confirm)){
                    break;
                }else{
                    System.out.println("=======================");
                    System.out.println("�н����尡 ��ġ���� �ʽ��ϴ�.");
                    System.out.println("�н����带 �ٽ� �Է����ּ���.");
                    System.out.println("=======================");
                }
            }

            //�̸�
            System.out.print("����: ");
            String name = sc.nextLine();

            //�������(6�ڸ�)
            String birth_date="";
            while(true){
                System.out.print("�������(6�ڸ�): ");
                birth_date = sc.nextLine();
                if(birth_date.length()==6){
                    break;
                }else{
                    System.out.println("=======================");
                    System.out.println("������� �ڸ����� �ùٸ��� �ʽ��ϴ�.");
                    System.out.println("��������� �ٽ� �Է����ּ���.");
                    System.out.println("=======================");
                }
            }

            //�̸���
            System.out.print("�̸���: ");
            String email = sc.nextLine();

            info[i] = new userinfo(username,password,name,birth_date,email);
            System.out.println("-----------------------");
            System.out.println(info[i].name+"��, ������ ȯ���մϴ�.");
            System.out.println("ID�� "+info[i].id+" �Դϴ�");
            System.out.println("-----------------------");
            i++;
            register=false;
            while(!register){
                System.out.println(i+" ��° ȸ�������� ���� �Ͻðڽ��ϱ�?\ny:����    N:���     I:ȸ������ ���� ���");
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
                        System.out.print("����ϱ� ���ϴ� ȸ������ ������ ��ȣ�� �Է����ּ���");
                        cnt1 = sc.nextLine();
                        cnt2 = Integer.parseInt(cnt1);
                        if (cnt2 < i) {
                            info[cnt2].infoprint(cnt2);
                            register2 = true;
                        } else {
                            System.out.println("�߸��� ���� �Է��Ͽ����ϴ�.");
                            System.out.println("=======================");
                        }
                    }
                }else{
                    System.out.println("�߸��� ���� �Է��Ͽ����ϴ�.");
                    System.out.println("=======================");
                }
            }
        }
    }
}
