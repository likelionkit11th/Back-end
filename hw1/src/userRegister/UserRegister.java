package userRegister;

import menus.Menu;
import users.User;
import users.Users;

import java.util.Scanner;

public class UserRegister {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Users users = new Users();
        Menu.showMainMenu(sc);

        while (true) {
            User user = Menu.showRegisterMenu(sc, users);
            Menu.showGreetingMenu(user);
            Menu.showNewRegisterMenu(sc);
        }
    }
}