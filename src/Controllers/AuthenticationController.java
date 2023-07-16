package Controllers;

import Infrastructure.DatabaseMysql;
import Model.Authentication;

import java.sql.*;
import java.util.Objects;
import java.util.Scanner;
import javax.swing.JOptionPane;
public class AuthenticationController {
    public static boolean login(String login, String senha) {
        return Authentication.login(login, senha);
    }
}
