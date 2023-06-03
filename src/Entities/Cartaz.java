package Entities;

import Controllers.AcervoControler;

import javax.sound.midi.Soundbank;
import javax.swing.*;
import java.util.Scanner;

enum EnumCartaz {
    POLITICO,
    CULTURAL,
    EDUCATIVO
};

public class Cartaz extends Documento {
    protected EnumCartaz tipo;

    @Override
    public void cadastrar(){
        super.cadastrar();
        Scanner scan = new Scanner(System.in);
        System.out.println("Insira o tipo do cartaz: ");
        System.out.println("1 - Político");
        System.out.println("2 - Cultural");
        System.out.println("3 - Educativo");
        int tipo = scan.nextInt();
        switch (tipo) {
            case 1 -> this.tipo = EnumCartaz.POLITICO;
            case 2 -> this.tipo = EnumCartaz.CULTURAL;
            case 3 -> this.tipo = EnumCartaz.EDUCATIVO;
            default -> System.out.println("Tipo inválido");
        }
    }

    @Override
    public void imprimir() {
        super.imprimir();
        System.out.println("Tipo: " + tipo);
    }

}
