package Entities;
import java.util.Scanner;

public class Livro extends Documento {
    protected String isbn;

    @Override
    public void cadastrar(){
        super.cadastrar();
        Scanner scan = new Scanner(System.in);
        System.out.println("Insira o isbn");
        isbn = scan.nextLine();
    }

    @Override
    public void imprimir() {
        super.imprimir();
        System.out.println("ISBN: " + isbn);
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public void imprimirFicha(){
        super.imprimirFicha();
        System.out.printf("\nISBN: %s", getIsbn());
    }
}
