package utils;

import java.util.Scanner;

public class Utils {
    public static boolean optionSelector(Scanner sc) {

        while (true) {
            System.out.print(">> ");
            String input = sc.nextLine();

            if (input.equalsIgnoreCase("y")){
                return true;
            } else if (input.equalsIgnoreCase("n")) {
                return false;
            } else {
                System.out.println("입력 값을 확인해주세요, (y/N)");
            }
        }
    }
}
