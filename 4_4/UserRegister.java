import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class UserRegister {
    public static void main(String[] args){
        System.out.println("=======================");
        System.out.println("ȸ�����");
        System.out.println("=======================");
        boolean register = false;
        Scanner sc = new Scanner(System.in);
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
        ArrayList users= new ArrayList();
        while(true){
            HashMap user = new HashMap();

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
                System.out.print("�ȳ����(6�ڸ�): ");
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

            user.put("username",username);
            user.put("password",password);
            user.put("name",name);
            user.put("birth_date",birth_date);
            user.put("email",email);

            users.add(user);

            System.out.println("-----------------------");
            System.out.println(user.get("name")+"��, ������ ȯ���մϴ�.");
            System.out.println("ID�� "+user.get("username")+" �Դϴ�");
            System.out.println("-----------------------");


            System.out.println("ȸ�������� �̾ �Ͻðڽ��ϱ�?\ny:����    N:���");
            System.out.print(">>");
            String register_again = sc.nextLine();
            if (register_again.equalsIgnoreCase("y")){
                ;
            }else if(register_again.equalsIgnoreCase("n")){
                System.exit(0);
            }
        }
    }
}
