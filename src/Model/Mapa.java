package Model;

import Infrastructure.DatabaseMysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

enum EnumMapa {
    POLITICO,
    DEMOGRAFICO,
    HISTORICO,
    ECONOMICO
};

public class Mapa extends Documento {

    protected EnumMapa tipo;

    @Override
    public void inserirNoBanco(AcervoBase acervo){
        super.inserirNoBanco(acervo);
        DatabaseMysql db = new DatabaseMysql();
        String sql = "UPDATE acervo SET tipo_mapa = '" + tipo.name() + "', tipo = 'Mapa' WHERE id = '" + id + "';";
        db.execute(sql);
    }

    public static Object obterDoBanco(int id, Mapa acervo){
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
                acervo.setTipo(EnumMapa.valueOf(rs.getString("tipo_mapa")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return acervo;
    }

    public EnumMapa getTipo() {
        return tipo;
    }

    public void setTipo(EnumMapa tipo) {
        this.tipo = tipo;
    }

}
