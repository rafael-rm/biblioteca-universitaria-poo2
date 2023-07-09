package Controllers;
import Entities.*;
import Infrastructure.DatabaseMysql;
import Others.Menu;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
        DatabaseMysql db = new DatabaseMysql();
        Connection conn = db.getConnection();
        String sql = "SELECT MAX(id) FROM acervo";
        int idCounter = 0;
        try {
            var stmt = conn.prepareStatement(sql);
            var rs = stmt.executeQuery();
            while (rs.next()) {
                idCounter = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return idCounter;
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
        item.inserirNoBanco(item);
        controller.listAcervos.add(item);
        System.out.println("Item cadastrado com sucesso!");
    }

    public boolean removerAcervo(int id){
        DatabaseMysql db = new DatabaseMysql();
        Connection conn = db.getConnection();
        String sql = "DELETE FROM acervo WHERE id = '" + id + "';";
        try {
            var stmt = conn.prepareStatement(sql);
            var rs = stmt.executeUpdate();
            return rs != 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean editarAcervo(int id){
        boolean status = getItem(id);

        if (!status) return false;

        AcervoBase item = new AcervoBase();
        item.cadastrar();
        item.atualizarNoBanco(item, id);
        return true;
    }

    public boolean imprimirFichaCatalografica(int id){
        boolean status = getItem(id);
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

    public boolean getItem (int id){
        DatabaseMysql db = new DatabaseMysql();
        Connection conn = db.getConnection();
        String sql = "SELECT * FROM acervo WHERE id = " + id;
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void pesquisarItem (int id) {
        DatabaseMysql db = new DatabaseMysql();
        Connection conn = db.getConnection();
        String sql = "SELECT * FROM acervo WHERE id = " + id;
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                System.out.printf("ID: %d, Nome: %s, Quantidade exemplares: %d%n", rs.getInt("id"), rs.getString("titulo"), rs.getInt("qtd_exemplares"));
            } else {
                System.out.println("Item não encontrado!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int emprestarItem(int id) {
        boolean status = getItem(id);
        if (!status) {
            System.out.println("Item não encontrado!");
            return ITEM_NAO_ENCONTRADO;
        }

        DatabaseMysql db = new DatabaseMysql();
        Connection conn = db.getConnection();
        String sql = "SELECT * FROM acervo WHERE id = " + id;

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                int qtd_exemplares = rs.getInt("qtd_exemplares");
                int emprestados = rs.getInt("emprestados");
                if (qtd_exemplares > emprestados) {
                    sql = "UPDATE acervo SET emprestados = " + (emprestados + 1) + " WHERE id = " + id;
                    stmt.executeUpdate(sql);
                    return ITEM_EMPRESTADO;
                } else {
                    return ITEM_SEM_EXEMPLAR_DISPONIVEL;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ITEM_SEM_EXEMPLAR_DISPONIVEL;
    }
}
