package Views;

import Controllers.AcervoControler;
import Model.AcervoBase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private JLabel textisbn;
    private JLabel isbn;
    private JLabel cdu;
    private JLabel textCdu;
    private JLabel Cdu;
    private JButton btnOk;

    public TelaFicha(){

        setContentPane(Tela);
        setMinimumSize(new Dimension(400, 400));

        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AcervoBase item = new AcervoBase();

                int id = (Integer.parseInt(textId.getText()));

                item = AcervoControler.buscarAcervo(id);

                autor.setText(item.getTitulo());
            }
        });
    }

}
