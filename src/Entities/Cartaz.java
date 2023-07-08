package Entities;

import Controllers.AcervoControler;
import Infrastructure.DatabaseMysql;

import javax.sound.midi.Soundbank;
import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

enum EnumCartaz {
    POLITICO,
    CULTURAL,
    EDUCATIVO
};

public class Cartaz extends Documento {
    protected EnumCartaz tipo;

    @Override
    public void cadastrar(){
        super.cadastrar();
        Scanner scan = new Scanner(System.in);
        System.out.println("Insira o tipo do cartaz: ");
        System.out.println("1 - Político");
        System.out.println("2 - Cultural");
        System.out.println("3 - Educativo");
        int tipo = scan.nextInt();
        switch (tipo) {
            case 1 -> this.tipo = EnumCartaz.POLITICO;
            case 2 -> this.tipo = EnumCartaz.CULTURAL;
            case 3 -> this.tipo = EnumCartaz.EDUCATIVO;
            default -> System.out.println("Tipo inválido");
        }
    }

    @Override
    public void inserirNoBanco(AcervoBase acervo){
        super.inserirNoBanco(acervo);
        DatabaseMysql db = new DatabaseMysql();
        String sql = "UPDATE acervo SET tipo_cartaz = '" + tipo.name() + "', tipo = 'Cartaz' WHERE id = '" + id + "';";
        db.execute(sql);
    }

    public static Object obterDoBanco(int id, Cartaz acervo){
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
                acervo.setTipo(EnumCartaz.valueOf(rs.getString("tipo_cartaz")));
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

    public void setTipo(EnumCartaz tipo) {
        this.tipo = tipo;
    }

}
