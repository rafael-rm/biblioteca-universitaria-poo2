package Entities;

import Infrastructure.DatabaseMysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Utils {
    public static Object obterDoBanco(int id){
        var obj = getTipo(id);
        return switch (obj) {
            case Livro livro -> Livro.obterDoBanco(id, livro);
            case Cartaz cartaz -> Cartaz.obterDoBanco(id, cartaz);
            case Relatorio relatorio -> Relatorio.obterDoBanco(id, relatorio);
            case Periodico periodico -> Periodico.obterDoBanco(id, periodico);
            case Midia midia -> Midia.obterDoBanco(id, midia);
            case Tcc tcc -> Tcc.obterDoBanco(id, tcc);
            case Mapa mapa -> Mapa.obterDoBanco(id, mapa);
            case null, default -> AcervoBase.obterDoBanco(id, (AcervoBase) obj);
        };
    }

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
                AcervoBase acervo = (AcervoBase) getTipo(rs.getInt("id"));
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
                acervos.add(acervo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return acervos;
    }
}
