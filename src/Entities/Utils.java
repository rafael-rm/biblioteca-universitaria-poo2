package Entities;

import Infrastructure.DatabaseMysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Utils {
    public static Object obterDoBanco(int id){
        var obj = getTipo(id);
        if (obj instanceof Livro){
            return Livro.obterDoBanco(id, (Livro) obj);
        } else if (obj instanceof Cartaz){
            return Cartaz.obterDoBanco(id, (Cartaz) obj);
        } else if (obj instanceof Relatorio){
            return Relatorio.obterDoBanco(id, (Relatorio) obj);
        } else if (obj instanceof Periodico){
            return Periodico.obterDoBanco(id, (Periodico) obj);
        } else if (obj instanceof Midia){
            return Midia.obterDoBanco(id, (Midia) obj);
        } else if (obj instanceof Tcc){
            return Tcc.obterDoBanco(id, (Tcc) obj);
        } else if (obj instanceof Mapa){
            return Mapa.obterDoBanco(id, (Mapa) obj);
        } else{
            return AcervoBase.obterDoBanco(id, (AcervoBase) obj);
        }
    }

    public static Object getTipo(int id) {
        DatabaseMysql db = new DatabaseMysql();
        Connection conn = db.getConnection();
        String sql = "SELECT tipo FROM acervo WHERE id = " + id + ";";
        String tipo = "";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
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
}