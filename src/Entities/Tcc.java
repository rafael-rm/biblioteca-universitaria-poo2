package Entities;

import java.util.Scanner;

enum EnumTcc {
    MONOGRAFIA,
    DISSERTACAO,
    TESE
};

public class Tcc extends Documento {
    protected EnumTcc tipo;

    @Override
    public void cadastrar(){
        super.cadastrar();
        Scanner scan = new Scanner(System.in);
        System.out.println("Insira o tipo de TCC: ");
        System.out.println("1 - Monografia");
        System.out.println("2 - Dissertação");
        System.out.println("3 - Tese");
        int tipo = scan.nextInt();
        switch (tipo) {
            case 1 -> this.tipo = EnumTcc.MONOGRAFIA;
            case 2 -> this.tipo = EnumTcc.DISSERTACAO;
            case 3 -> this.tipo = EnumTcc.TESE;
            default -> System.out.println("Tipo inválido");
        }
    }

    @Override
    public void imprimir() {
        super.imprimir();
        System.out.println("Tipo: " + tipo);
    }
}
