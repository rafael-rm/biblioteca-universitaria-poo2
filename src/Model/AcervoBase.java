package Model;

import Controllers.AcervoControler;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
import Infrastructure.DatabaseMysql;
import Views.TelaCadastro;

public class AcervoBase {
    protected String tipoAcervo;
    protected int id;
    protected String titulo;
    protected String autor;
    protected int edicao;
    protected String cidade;
    protected String editora;
    protected int ano;
    protected String cdu;
    protected String assunto;
    protected ArrayList<String> palavras_chave;
    protected String palavras_chave_string;
    protected int qtd_exemplares;
    protected int emprestados;
    protected int num_pag;
    protected float tam_pag;


    public AcervoBase() {
        id = 0;
        titulo = "Default";
        autor = "Default";
        edicao = 0;
        cidade = "Default";
        editora = "Default";
        ano = 0;
        cdu = "Default";
        assunto = "Default";
        palavras_chave = new ArrayList<String>();
        palavras_chave_string = "Default";
        qtd_exemplares = 0;
        emprestados = 0;
    }

    private TelaCadastro tc;

    public void cadastrar(){

        Scanner scan = new Scanner(System.in);
        AcervoControler controller = AcervoControler.getInstance("prod");
        int qtdAutores, qtdPalavrasChave;

        System.out.println("Digite o título: ");
        titulo = scan.nextLine();
        System.out.println("Digite a quantidade de autores: ");
        qtdAutores = scan.nextInt();
        System.out.println("Digite a edição: ");
        scan = new Scanner(System.in);
        edicao = scan.nextInt();
        System.out.println("Digite a cidade: ");
        scan = new Scanner(System.in);
        cidade = scan.nextLine();
        System.out.println("Digite a editora: ");
        editora = scan.nextLine();
        System.out.println("Digite o ano: ");
        scan = new Scanner(System.in);
        ano = scan.nextInt();
        System.out.println("Digite o CDU: ");
        scan = new Scanner(System.in);
        cdu = scan.nextLine();
        System.out.println("Digite o assunto: ");
        assunto = scan.nextLine();
        System.out.println("Digite a quantidade de palavras chave: ");
        scan = new Scanner(System.in);
        qtdPalavrasChave = scan.nextInt();
        for (int i = 0; i < qtdPalavrasChave; i++) {
            String palavraChave;
            System.out.println("Digite a palavra chave: ");
            scan = new Scanner(System.in);
            palavraChave = scan.nextLine();
            palavras_chave.add(palavraChave);
        }
        System.out.println("Digite a quantidade de exemplares: ");
        scan = new Scanner(System.in);
        qtd_exemplares = scan.nextInt();

        if (id == 0) {
            id = AcervoBase.getIdCounter();
            id++;
        }

        palavras_chave_string = "";
        for (int i = 0; i < palavras_chave.size(); i++) {
            palavras_chave_string += palavras_chave.get(i);
            if (i != palavras_chave.size() - 1)
                palavras_chave_string +=" , ";
        }
    }

    public void inserirNoBanco(AcervoBase acervo){
        DatabaseMysql db = new DatabaseMysql();
        String sql = "INSERT INTO acervo (id, tipo, titulo, edicao, cidade, editora, ano, cdu, assunto, palavras_chave, qtd_exemplares, tam_pag, num_pag, emprestados) VALUES (" +
                acervo.getId() + ", '" +
                acervo.getTipoAcervo() + "', '" +
                acervo.getTitulo() + "', " +
                acervo.getEdicao() + ", '" +
                acervo.getCidade() + "', '" +
                acervo.getEditora() + "', " +
                acervo.getAno() + ", '" +
                acervo.getCdu() + "', '" +
                acervo.getAssunto() + "', '" +
                acervo.getPalavras_chave_string() + "', " +
                acervo.getQtd_exemplares() + ", " +
                acervo.getTam_pag() + ", " +
                acervo.getNum_pag() + ", " +
                acervo.getEmprestados() + ");";
        db.execute(sql);
    }

    public void atualizarNoBanco(AcervoBase acervo, int id){
        DatabaseMysql db = new DatabaseMysql();
        String sql = "UPDATE acervo SET " +
                "titulo = '" + acervo.getTitulo() + "', " +
                "edicao = " + acervo.getEdicao() + ", " +
                "cidade = '" + acervo.getCidade() + "', " +
                "editora = '" + acervo.getEditora() + "', " +
                "ano = " + acervo.getAno() + ", " +
                "cdu = '" + acervo.getCdu() + "', " +
                "assunto = '" + acervo.getAssunto() + "', " +
                "palavras_chave = '" + acervo.getPalavras_chave_string() + "', " +
                "qtd_exemplares = " + acervo.getQtd_exemplares() + ", " +
                "emprestados = " + acervo.getEmprestados() + " " +
                "tam_pag = " + acervo.getTam_pag() + " " +
                "num_pag = " + acervo.getNum_pag() + " " +
                "WHERE id = " + id + ";";
        db.execute(sql);
    }

    public static Object obterDoBanco(int id, AcervoBase acervo){
        DatabaseMysql db = new DatabaseMysql();
        Connection conn = db.getConnection();
        String sql = "SELECT * FROM acervo WHERE id = " + id + ";";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                acervo.setId(rs.getInt("id"));
                acervo.setTitulo(rs.getString("titulo"));
                acervo.setEdicao(rs.getInt("edicao"));
                acervo.setCidade(rs.getString("cidade"));
                acervo.setEditora(rs.getString("editora"));
                acervo.setAno(rs.getInt("ano"));
                acervo.setCdu(rs.getString("cdu"));
                acervo.setAssunto(rs.getString("assunto"));
                acervo.setPalavras_chave_string(rs.getString("palavras_chave"));
                acervo.setQtd_exemplares(rs.getInt("qtd_exemplares"));
                acervo.setEmprestados(rs.getInt("emprestados"));
                acervo.setTam_pag(rs.getInt("tam_pag"));
                acervo.setNum_pag(rs.getInt("num_pag"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return acervo;
    }

    public void imprimir(){
        System.out.println("ID: " + id);
        System.out.println("Título: " + titulo);
        System.out.println("Autores: ");

        System.out.println("Edição: " + edicao);
        System.out.println("Cidade: " + cidade);
        System.out.println("Editora: " + editora);
        System.out.println("Ano: " + ano);
        System.out.println("CDU: " + cdu);
        System.out.println("Assunto: " + assunto);
        System.out.println("Palavras chave:");
        for (int i = 0; i < palavras_chave.size(); i++) {
            System.out.print(" " + palavras_chave.get(i));
        }
        System.out.println("\nQuantidade de exemplares: " + qtd_exemplares);
        System.out.println("Quantidade de exemplares emprestados: " + emprestados);
    }

    public void imprimirFicha(){
        System.out.println("-----------------------------------------------------------------------------------------");
    }

    public static boolean getItem (int id){
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

    public static void pesquisarItem (int id) {
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

    public static int getIdCounter() {
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

    public static int getNextId() {
        return getIdCounter() + 1;
    }

    public static boolean removerAcervo(int id){
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

    public static int emprestarItem(int id) {
        boolean status = getItem(id);
        if (!status) {
            System.out.println("Item não encontrado!");
            return AcervoControler.ITEM_NAO_ENCONTRADO;
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
                    return AcervoControler.ITEM_EMPRESTADO;
                } else {
                    return AcervoControler.ITEM_SEM_EXEMPLAR_DISPONIVEL;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return AcervoControler.ITEM_SEM_EXEMPLAR_DISPONIVEL;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getEdicao() {
        return edicao;
    }

    public void setEdicao(int edicao) {
        this.edicao = edicao;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEditora() {
        return editora;
    }

    public String getTipoAcervo() {
        return tipoAcervo;
    }

    public void setTipoAcervo(String tipoAcervo) {
        this.tipoAcervo = tipoAcervo;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getCdu() {
        return cdu;
    }

    public void setCdu(String cdu) {
        this.cdu = cdu;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public ArrayList<String> getPalavras_chave() {
        return palavras_chave;
    }

    public void setPalavras_chave(ArrayList<String> palavras_chave) {
        this.palavras_chave = palavras_chave;
    }

    public int getQtd_exemplares() {
        return qtd_exemplares;
    }

    public void setQtd_exemplares(int qtd_exemplares) {
        this.qtd_exemplares = qtd_exemplares;
    }

    public int getEmprestados() {
        return emprestados;
    }

    public void setEmprestados(int emprestados) {
        this.emprestados = emprestados;
    }

    public String getPalavras_chave_string() {
        return palavras_chave_string;
    }

    public void setPalavras_chave_string(String palavras_chave_string) {
        this.palavras_chave_string = palavras_chave_string;
    }

    public int getId() {
        return id;
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
}
