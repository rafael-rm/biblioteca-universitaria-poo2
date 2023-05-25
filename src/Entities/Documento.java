package Entities;

import java.util.Scanner;

public class Documento extends AcervoBase {
    public int num_pag;
    public float tam_pag;

    @Override
    public void cadastrar(){
        super.cadastrar();
        Scanner scan = new Scanner(System.in);
        System.out.println("Insira o número de páginas: ");
        num_pag = scan.nextInt();
        System.out.println("Insira o tamanho da página: ");
        scan = new Scanner(System.in);
        tam_pag = scan.nextFloat();
    }
}
