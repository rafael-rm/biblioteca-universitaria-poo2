package Entities;

import Infrastructure.DatabaseMysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

enum EnumMidia {
    FITA,
    VHS,
    CD,
    DVD
};

public class Midia extends AcervoBase {
    protected EnumMidia tipo;

    @Override
    public void cadastrar(){
        super.cadastrar();
        Scanner scan = new Scanner(System.in);
        System.out.println("Insira o tipo de midia: ");
        System.out.println("1 - Fita");
        System.out.println("2 - VHS");
        System.out.println("3 - CD");
        System.out.println("4 - DVD");
        int tipo = scan.nextInt();
        switch (tipo) {
            case 1 -> this.tipo = EnumMidia.FITA;
            case 2 -> this.tipo = EnumMidia.VHS;
            case 3 -> this.tipo = EnumMidia.CD;
            case 4 -> this.tipo = EnumMidia.DVD;
            default -> System.out.println("Tipo inválido");
        }
    }

    @Override
    public void imprimir() {
        super.imprimir();
        System.out.println("Tipo: " + tipo);
    }

    @Override
    public void inserirNoBanco(AcervoBase acervo){
        super.inserirNoBanco(acervo);
        DatabaseMysql db = new DatabaseMysql();
        String sql = "UPDATE acervo SET tipo_midia = '" + tipo.name() + "', tipo = 'Midia' WHERE id = '" + id + "';";
        db.execute(sql);
    }

    public static Object obterDoBanco(int id, Midia acervo){
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
                acervo.setTipo(rs.getString("tipo_midia"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return acervo;
    }

    public EnumMidia getTipo() {
        return tipo;
    }

    public void setTipo(EnumMidia tipo) {
        this.tipo = tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = EnumMidia.valueOf(tipo);
    }

    public String getTipoString() {
        return tipo.name();
    }
}
