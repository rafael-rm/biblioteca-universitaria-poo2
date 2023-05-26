package Controllers;
import Entities.AcervoBase;
import Entities.Cartaz;
import Entities.Livro;
import Entities.Mapa;
import Entities.Midia;
import Entities.Periodico;
import Entities.Relatorio;
import Entities.Tcc;
import Others.Menu;
import java.util.ArrayList;
import java.util.Scanner;

import static Others.Menu.LISTAR_LIVROS;
import static Others.Menu.LISTAR_TODOS_ITENS;

public class AcervoControler {
    private static AcervoControler instance;
    private ArrayList<AcervoBase> listAcervos;
    private int idCounter;
    public String value;

    private AcervoControler(String value) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        this.listAcervos = new ArrayList<>();
        this.value = value;
        this.idCounter = 0;
    }

    public static AcervoControler getInstance(String value) {
        if (instance == null) {
            instance = new AcervoControler(value);
        }
        return instance;
    }

    public int getIdCounter() {
        this.idCounter++;
        return this.idCounter;
    }

    public void cadastrarAcervo(){
        AcervoControler controller = AcervoControler.getInstance("prod");
        int opcao = Menu.menuCadastrar();
        if (opcao == 0) return;

        new AcervoBase();
        AcervoBase item = switch (opcao) {
            case 1 -> new Livro();
            case 2 -> new Mapa();
            case 3 -> new Periodico();
            case 4 -> new Tcc();
            case 5 -> new Relatorio();
            case 6 -> new Cartaz();
            case 7 -> new Midia();
            default -> throw new IllegalStateException("Unexpected value: " + opcao);
        };
        item.cadastrar();
        controller.listAcervos.add(item);
        System.out.println("Item cadastrado com sucesso!");
    }

    public void listarAcervo(){
        AcervoControler controller = AcervoControler.getInstance("prod");
        Scanner sc = new Scanner(System.in);
        int opcao = Menu.menuListar();
        if (opcao == 0) return;

        if (controller.listAcervos.isEmpty()){
            System.out.println("Não há itens cadastrados!");
            System.out.println("Pressione enter para continuar...");
            sc.nextLine();
            return;
        }

        int count = 0;
        switch (opcao){
            case LISTAR_TODOS_ITENS -> {
                for (AcervoBase item : controller.listAcervos) {
                    System.out.printf("ID: %d, Nome: %s, Quantidade exemplares: %d%n", item.getId(), item.getTitulo(), item.getQtd_exemplares());
                }
                System.out.println("Pressione enter para continuar...");
                sc.nextLine();
            }
            case LISTAR_LIVROS -> {
                for (AcervoBase item : controller.listAcervos) {
                    if (item instanceof Livro){
                        count++;
                        System.out.printf("ID: %d, Nome: %s, Quantidade exemplares: %d%n", item.getId(), item.getTitulo(), item.getQtd_exemplares());
                    }
                }

                if (count == 0)
                    System.out.println("Não há livros cadastrados!");

                System.out.println("Pressione enter para continuar...");
                sc.nextLine();
            }
            case Menu.LISTAR_MAPAS -> {
                for (AcervoBase item : controller.listAcervos) {
                    if (item instanceof Mapa){
                        count++;
                        System.out.printf("ID: %d, Nome: %s, Quantidade exemplares: %d%n", item.getId(), item.getTitulo(), item.getQtd_exemplares());
                    }
                }

                if (count == 0)
                    System.out.println("Não há mapas cadastrados!");

                System.out.println("Pressione enter para continuar...");
                sc.nextLine();
            }
            case Menu.LISTAR_PERIODICOS -> {
                for (AcervoBase item : controller.listAcervos) {
                    if (item instanceof Periodico){
                        count++;
                        System.out.printf("ID: %d, Nome: %s, Quantidade exemplares: %d%n", item.getId(), item.getTitulo(), item.getQtd_exemplares());
                    }
                }

                if (count == 0)
                    System.out.println("Não há periódicos cadastrados!");

                System.out.println("Pressione enter para continuar...");
                sc.nextLine();
            }
            case Menu.LISTAR_TCCS -> {
                for (AcervoBase item : controller.listAcervos) {
                    if (item instanceof Tcc){
                        count++;
                        System.out.printf("ID: %d, Nome: %s, Quantidade exemplares: %d%n", item.getId(), item.getTitulo(), item.getQtd_exemplares());
                    }
                }

                if (count == 0)
                    System.out.println("Não há TCCs cadastrados!");

                System.out.println("Pressione enter para continuar...");
                sc.nextLine();
            }
            case Menu.LISTAR_RELATORIOS -> {
                for (AcervoBase item : controller.listAcervos) {
                    if (item instanceof Relatorio){
                        count++;
                        System.out.printf("ID: %d, Nome: %s, Quantidade exemplares: %d%n", item.getId(), item.getTitulo(), item.getQtd_exemplares());
                    }
                }

                if (count == 0)
                    System.out.println("Não há relatórios cadastrados!");

                System.out.println("Pressione enter para continuar...");
                sc.nextLine();
            }
            case Menu.LISTAR_CARTAZES -> {
                for (AcervoBase item : controller.listAcervos) {
                    if (item instanceof Cartaz){
                        count++;
                        System.out.printf("ID: %d, Nome: %s, Quantidade exemplares: %d%n", item.getId(), item.getTitulo(), item.getQtd_exemplares());
                    }
                }

                if (count == 0)
                    System.out.println("Não há cartazes cadastrados!");

                System.out.println("Pressione enter para continuar...");
                sc.nextLine();
            }
            case Menu.LISTAR_MIDIAS -> {
                for (AcervoBase item : controller.listAcervos) {
                    if (item instanceof Midia){
                        count++;
                        System.out.printf("ID: %d, Nome: %s, Quantidade exemplares: %d%n", item.getId(), item.getTitulo(), item.getQtd_exemplares());
                    }
                }

                if (count == 0)
                    System.out.println("Não há mídias cadastradas!");

                System.out.println("Pressione enter para continuar...");
                sc.nextLine();
            }
            default -> throw new IllegalStateException("Unexpected value: " + opcao);
        }
    }
}
