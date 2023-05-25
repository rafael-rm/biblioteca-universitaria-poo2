package Entities;

import java.util.ArrayList;
import java.util.Scanner;

public class Autor {
    public String nome;
    public String dataNascimento;
    public ArrayList<AcervoBase> obras;

    public void cadastrar () {
        Scanner scan = new Scanner(System.in);
        System.out.println("Digite o nome do autor: ");
        nome = scan.nextLine();
        System.out.println("Digite a data de nascimento do autor: ");
        dataNascimento = scan.nextLine();
    }
}
