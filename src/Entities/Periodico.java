package Entities;

import java.util.Scanner;

enum EnumPeriodico {
    REVISTA,
    JORNAL
};

public class Periodico extends Documento {
    public String ISSN;
    public EnumPeriodico tipo;

    @Override
    public void cadastrar(){
        super.cadastrar();
        Scanner scan = new Scanner(System.in);
        System.out.println("Insira o tipo de periodico: ");
        System.out.println("1 - Revista");
        System.out.println("2 - Jornal");
        int tipo = scan.nextInt();
        switch (tipo) {
            case 1 -> this.tipo = EnumPeriodico.REVISTA;
            case 2 -> this.tipo = EnumPeriodico.JORNAL;
            default -> System.out.println("Tipo inválido");
        }
    }
}
