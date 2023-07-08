package Entities;

import Infrastructure.DatabaseMysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Documento extends AcervoBase {
    protected int num_pag;
    protected float tam_pag;

    @Override
    public void cadastrar(){
        super.cadastrar();
        Scanner scan = new Scanner(System.in);
        System.out.println("Insira o número de páginas: ");
        num_pag = scan.nextInt();
        System.out.println("Insira o tamanho da página: ");
        scan = new Scanner(System.in);
        tam_pag = scan.nextFloat();
    }

    @Override
    public void inserirNoBanco(AcervoBase acervo){
        super.inserirNoBanco(acervo);
        DatabaseMysql db = new DatabaseMysql();
        String sql = "UPDATE acervo SET num_pag = '" + num_pag + "', tam_pag = '" + tam_pag + "', tipo = 'Documento' WHERE id = '" + id + "';";
        db.execute(sql);
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

    @Override
    public void imprimir() {
        super.imprimir();
        System.out.println("Número de páginas: " + num_pag);
        System.out.println("Tamanho da página: " + tam_pag);
    }

    @Override
    public void imprimirFicha(){
        super.imprimirFicha();
        System.out.printf("\n%d pág.: %f cm", getNum_pag(), getTam_pag());
        System.out.printf("\n1. %s. |.",getAssunto());
        for (int i = 0; i < palavras_chave.size(); i++) {
            System.out.printf(" %s %d.", palavras_chave.get(i), i+1);
        }

        System.out.printf("\n\n\t\t\tCDU: %s\n\n", getCdu());
    }

    public static Object obterDoBanco(int id, Documento acervo){
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
}
