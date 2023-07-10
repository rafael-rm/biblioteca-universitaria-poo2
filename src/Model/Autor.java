package Model;

import java.util.ArrayList;
import java.util.Scanner;

public class Autor {
    protected String nome;
    protected String dataNascimento;
    protected ArrayList<AcervoBase> obras;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public ArrayList<AcervoBase> getObras() {
        return obras;
    }

    public void setObras(ArrayList<AcervoBase> obras) {
        this.obras = obras;
    }

    public void cadastrar () {
        Scanner scan = new Scanner(System.in);
        System.out.println("Digite o nome do autor: ");
        nome = scan.nextLine();
        System.out.println("Digite a data de nascimento do autor: ");
        dataNascimento = scan.nextLine();
    }
}
