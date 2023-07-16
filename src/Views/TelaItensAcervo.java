package Views;

import Controllers.AcervoControler;

import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;


public class TelaItensAcervo extends JFrame {
    private JPanel Itens;
    private JLabel JItens;
    private JLabel JImg;
    private JList<String> list;
    private JComboBox comboBox1;
    private JLabel JLabelOp;
    private JButton btnExcluir;
    private JButton btnEmprestar;
    private JLabel JtextOp;
    private JButton btnAtualizar;
    private JButton btnListar;
    private JButton btnVoltar;


    public TelaItensAcervo() {

        setContentPane(Itens);
        setMinimumSize(new Dimension(600, 500));

        btnExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = list.getSelectedIndex();
                String item = list.getModel().getElementAt(id);
                System.out.println(item);

//                boolean status = AcervoControler.removerAcervo();
//                if(status) {
//                    JOptionPane.showMessageDialog(null, "Item removido com sucesso!");
//                }else{
//                    JOptionPane.showMessageDialog(null, "Erro ao remover!");
//                }
            }
        });

        btnAtualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tipo = "Acervo";
                Vector<String> lista = AcervoControler.listarAcervo(tipo);
                list.setListData(lista);
            }
        });
        btnEmprestar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = comboBox1.getSelectedIndex();
                String tipo = (String) comboBox1.getItemAt(index);
                System.out.println(tipo);

                Vector<String> lista = AcervoControler.listarAcervo(tipo);
                list.setListData(lista);



            }
        });
        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaMenu tm = new TelaMenu();
                dispose();
                tm.setVisible(true);
            }
        });
    }

}

