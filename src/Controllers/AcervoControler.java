package Controllers;
import Model.*;
import Others.Menu;
import Views.TelaCadastro;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static Others.Menu.LISTAR_LIVROS;
import static Others.Menu.LISTAR_TODOS_ITENS;

public class AcervoControler {
    public static final int ITEM_EMPRESTADO = 1;
    public static final int ITEM_SEM_EXEMPLAR_DISPONIVEL = 2;
    public static final int ITEM_NAO_ENCONTRADO = 3;
    private static AcervoControler instance;
    private final ArrayList<AcervoBase> listAcervos;
    private final int idCounter;
    public String value;

    private static TelaCadastro tc;
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

    public static void cadastrarAcervo(){
        AcervoControler controller = AcervoControler.getInstance("prod");
        int opcao = Menu.menuCadastrar();
        if (opcao == 0) return;

        new AcervoBase();
        int selectedIndex = tc.cbTipo.getSelectedIndex();
        //AcervoBase item = switch (tc.cbTipo.getSelectedIndex()) {
        AcervoBase item = switch(selectedIndex){
            case 0-> new Livro();
            case 1 -> new Mapa();
            case 2 -> new Periodico();
            case 3 -> new Tcc();
            case 4 -> new Relatorio();
            case 5 -> new Cartaz();
            case 6 -> new Midia();
            default -> throw new IllegalStateException("Unexpected value: " + opcao);
        };
        item.cadastrar();
        item.inserirNoBanco(item);
        controller.listAcervos.add(item);
        System.out.println("Item cadastrado com sucesso!");
    }

    public boolean removerAcervo(int id){
        return AcervoBase.removerAcervo(id);
    }

    public boolean editarAcervo(int id){
        boolean status = AcervoBase.getItem(id);

        if (!status) return false;

        AcervoBase item = new AcervoBase();
        item.cadastrar();
        item.atualizarNoBanco(item, id);
        return true;
    }

    public boolean imprimirFichaCatalografica(int id){
        boolean status = AcervoBase.getItem(id);
        if (!status) return false;

        var obj = Utils.obterDoBanco(id);

        switch (obj) {
            case Livro livro -> livro.imprimirFicha();
            case Mapa mapa -> mapa.imprimirFicha();
            case Periodico periodico -> periodico.imprimirFicha();
            case Tcc tcc -> tcc.imprimirFicha();
            case Relatorio relatorio -> relatorio.imprimirFicha();
            case Cartaz cartaz -> cartaz.imprimirFicha();
            case Midia midia -> midia.imprimirFicha();
            case Documento documento -> documento.imprimirFicha();
            case AcervoBase acervoBase -> acervoBase.imprimirFicha();
            default -> throw new IllegalStateException("Unexpected value: " + obj);
        }
        return true;
    }

    public void listarAcervo() {
        AcervoControler controller = AcervoControler.getInstance("prod");
        Scanner sc = new Scanner(System.in);
        int opcao = Menu.menuListar();
        if (opcao == 0) return;

        List<AcervoBase> listAcervos = Utils.obterTodosDoBanco();

        if (listAcervos.isEmpty()) {
            System.out.println("Não há itens cadastrados!");
            System.out.println("Pressione enter para continuar...");
            sc.nextLine();
            return;
        }

        int count = 0;
        switch (opcao) {
            case LISTAR_TODOS_ITENS -> {
                for (AcervoBase item : listAcervos) {
                    System.out.printf("ID: %d, Nome: %s, Quantidade exemplares: %d%n", item.getId(), item.getTitulo(), item.getQtd_exemplares());
                }
                System.out.println("Pressione enter para continuar...");
                sc.nextLine();
            }
            case LISTAR_LIVROS -> {
                for (AcervoBase item : listAcervos) {
                    if (item instanceof Livro) {
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
                for (AcervoBase item : listAcervos) {
                    if (item instanceof Mapa) {
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
                for (AcervoBase item : listAcervos) {
                    if (item instanceof Periodico) {
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
                for (AcervoBase item : listAcervos) {
                    if (item instanceof Tcc) {
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
                for (AcervoBase item : listAcervos) {
                    if (item instanceof Relatorio) {
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
                for (AcervoBase item : listAcervos) {
                    if (item instanceof Cartaz) {
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
                for (AcervoBase item : listAcervos) {
                    if (item instanceof Midia) {
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
