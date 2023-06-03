package Entities;

import java.util.Scanner;

public class Documento extends AcervoBase {
    protected int num_pag;
    protected float tam_pag;

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

    public int getNum_pag() {
        return num_pag;
    }

    public void setNum_pag(int num_pag) {
        this.num_pag = num_pag;
    }

    public float getTam_pag() {
        return tam_pag;
    }

    public void setTam_pag(float tam_pag) {
        this.tam_pag = tam_pag;
    }

    @Override
    public void imprimir() {
        super.imprimir();
        System.out.println("Número de páginas: " + num_pag);
        System.out.println("Tamanho da página: " + tam_pag);
    }

    @Override
    public void imprimirFicha(){
        super.imprimirFicha();
        System.out.printf("\n%d pág.: %f cm", getNum_pag(), getTam_pag());
        System.out.printf("\n1. %s. |.",getAssunto());
        for (int i = 0; i < palavras_chave.size(); i++) {
            System.out.printf(" %s %d.", palavras_chave.get(i), i+1);
        }

        System.out.printf("\n\n\t\t\tCDU: %s\n\n", getCdu());
    }
}
