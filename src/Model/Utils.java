package Model;

import Infrastructure.DatabaseMysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static Object getTipo(int id) {
        DatabaseMysql db = new DatabaseMysql();
        Connection conn = db.getConnection();
        String sql = "SELECT tipo FROM acervo WHERE id = " + id + ";";
        String tipo = "";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                tipo = rs.getString("tipo");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return switch (tipo) {
            case "Livro" -> new Livro();
            case "Cartaz" -> new Cartaz();
            case "Relatorio" -> new Relatorio();
            case "Periodico" -> new Periodico();
            case "Midia" -> new Midia();
            case "Tcc" -> new Tcc();
            case "Mapa" -> new Mapa();
            default -> null;
        };
    }

    public static List<AcervoBase> obterTodosDoBanco(){
        DatabaseMysql db = new DatabaseMysql();
        Connection conn = db.getConnection();
        String sql = "SELECT * FROM acervo;";
        List<AcervoBase> acervos = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                AcervoBase acervo = new AcervoBase();
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
                acervo.setTipoAcervo(rs.getString("tipo"));
                acervos.add(acervo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return acervos;
    }
}
