package Model;

import Infrastructure.DatabaseMysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

enum EnumTcc {
    MONOGRAFIA,
    DISSERTACAO,
    TESE
};

public class Tcc extends Documento {

    protected EnumTcc tipo;

    @Override
    public void inserirNoBanco(AcervoBase acervo){
        super.inserirNoBanco(acervo);
        DatabaseMysql db = new DatabaseMysql();
        String sql = "UPDATE acervo SET tipo_tcc = '" + tipo.name() + "', tipo = 'Tcc' WHERE id = '" + id + "';";
        db.execute(sql);
    }

    public static Object obterDoBanco(int id, Tcc acervo){
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
                acervo.setTipo(EnumTcc.valueOf(rs.getString("tipo_tcc")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return acervo;
    }

    public EnumTcc getTipo() {
        return tipo;
    }

    public void setTipo(EnumTcc tipo) {
        this.tipo = tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = EnumTcc.valueOf(tipo);
    }

    public String getTipoString() {
        return tipo.name();
    }
}
