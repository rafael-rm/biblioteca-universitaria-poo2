package Entities;
import java.util.Scanner;

public class Livro extends Documento {
    public String isbn;

    @Override
    public void cadastrar(){
        super.cadastrar();
        Scanner scan = new Scanner(System.in);
        System.out.println("Insira o isbn");
        isbn = scan.nextLine();
    }
}
