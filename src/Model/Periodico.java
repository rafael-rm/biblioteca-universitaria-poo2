package Entities;

import Infrastructure.DatabaseMysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

enum EnumPeriodico {
    REVISTA,
    JORNAL
};

public class Periodico extends Documento {
    protected String ISSN;
    protected EnumPeriodico tipo;

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

    @Override
    public void inserirNoBanco(AcervoBase acervo){
        super.inserirNoBanco(acervo);
        DatabaseMysql db = new DatabaseMysql();
        String sql = "UPDATE acervo SET tipo_periodico = '" + tipo.name() + "', tipo = 'Periodico' WHERE id = '" + id + "';";
        db.execute(sql);
    }

    public static Object obterDoBanco(int id, Periodico acervo){
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
                acervo.setISSN(rs.getString("issn"));
                acervo.setTipo(rs.getString("tipo_periodico"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return acervo;
    }

    @Override
    public void imprimir() {
        super.imprimir();
        System.out.println("Tipo: " + tipo);
    }

    public String getISSN() {
        return ISSN;
    }

    public void setISSN(String ISSN) {
        this.ISSN = ISSN;
    }

    public EnumPeriodico getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = EnumPeriodico.valueOf(tipo);
    }
}
