package Entities;

import java.util.Scanner;

enum EnumMidia {
    FITA,
    VHS,
    CD,
    DVD
};

public class Midia extends AcervoBase {
    protected EnumMidia tipo;

    @Override
    public void cadastrar(){
        super.cadastrar();
        Scanner scan = new Scanner(System.in);
        System.out.println("Insira o tipo de midia: ");
        System.out.println("1 - Fita");
        System.out.println("2 - VHS");
        System.out.println("3 - CD");
        System.out.println("4 - DVD");
        int tipo = scan.nextInt();
        switch (tipo) {
            case 1 -> this.tipo = EnumMidia.FITA;
            case 2 -> this.tipo = EnumMidia.VHS;
            case 3 -> this.tipo = EnumMidia.CD;
            case 4 -> this.tipo = EnumMidia.DVD;
            default -> System.out.println("Tipo inválido");
        }
    }

    @Override
    public void imprimir() {
        super.imprimir();
        System.out.println("Tipo: " + tipo);
    }
}
