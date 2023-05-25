package Controllers;

import java.util.Objects;
import java.util.Scanner;

public class AuthenticationController {

    private String _login = "LOGIN";
    private String _senha = "SENHA";

    public boolean login() {
        Scanner scan = new Scanner(System.in);
        String login, senha;
        System.out.println("Login: ");
        login = scan.nextLine();
        System.out.println("Senha: ");
        senha = scan.nextLine();
        return Objects.equals(login, _login) && Objects.equals(senha, _senha);
    }
}

