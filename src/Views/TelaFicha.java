package Views;

import Controllers.AcervoControler;
import Model.AcervoBase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class TelaFicha extends JFrame {
    private JPanel Tela;
    private JLabel labeltext;
    private JTextField textId;
    private JPanel PanelFicha;
    private JLabel autor;
    private JLabel titulo;
    private JLabel edicao;
    private JLabel JEdicao;
    private JLabel cidade;
    private JLabel ano;
    private JLabel quantidade;
    private JLabel pag;
    private JLabel tamanho;
    private JLabel cm;
    private JLabel assunto;
    private JLabel palavra;
    private JLabel cdu;
    private JLabel textCdu;
    private JButton btnOk;
    private JButton btnCancelar;

    public TelaFicha(){

        setContentPane(Tela);
        setMinimumSize(new Dimension(400, 400));

        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AcervoBase item = new AcervoBase();

                int id = (Integer.parseInt(textId.getText()));

                item = AcervoControler.buscarAcervo(id);
                if (!Objects.equals(item.getTitulo(), "Default")) {

                    autor.setText(item.getAutor());
                    titulo.setText(item.getTitulo());
                    cidade.setText(item.getCidade());
                    edicao.setText(String.valueOf(item.getEdicao()));
                    ano.setText(String.valueOf(item.getAno()));
                    quantidade.setText(String.valueOf(item.getNum_pag()));
                    tamanho.setText(String.valueOf(item.getTam_pag()));
                    assunto.setText(item.getAssunto());
                    palavra.setText(item.getPalavras_chave_string());
                    cdu.setText(item.getCdu());

                } else {
                    JOptionPane.showMessageDialog(null, "Item não encontrado no acervo!");
                }
            }
        });

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaMenu tm = new TelaMenu();
                dispose();
                tm.setVisible(true);
            }
        });
    }
}
