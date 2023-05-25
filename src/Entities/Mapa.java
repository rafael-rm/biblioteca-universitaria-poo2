package Entities;

import java.util.Scanner;

enum EnumMapa {
    POLITICO,
    DEMOGRAFICO,
    HISTORICO,
    ECONOMICO
};

public class Mapa extends Documento {
    public EnumMapa tipo;

    @Override
    public void cadastrar() {
        super.cadastrar();
        Scanner scan = new Scanner(System.in);
        System.out.println("Insira o tipo do mapa: ");
        System.out.println("1 - Político");
        System.out.println("2 - Demográfico");
        System.out.println("3 - Histórico");
        System.out.println("3 - Econômico");
        int tipo = scan.nextInt();
        switch (tipo) {
            case 1 -> this.tipo = EnumMapa.POLITICO;
            case 2 -> this.tipo = EnumMapa.DEMOGRAFICO;
            case 3 -> this.tipo = EnumMapa.HISTORICO;
            case 4 -> this.tipo = EnumMapa.ECONOMICO;
            default -> System.out.println("Tipo inválido");
        }
    }
}
