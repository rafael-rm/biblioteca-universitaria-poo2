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
        setMinimumSize(new Dimension(700, 700));

        btnExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = list.getSelectedIndex();
                String item = list.getModel().getElementAt(id);

                int posicaoAbertura = item.indexOf("[");
                int posicaoFechamento = item.indexOf("]");

                int idAc = Integer.parseInt(item.substring(posicaoAbertura + 1, posicaoFechamento));

                boolean status = AcervoControler.removerAcervo(idAc);
                if(status) {
                    JOptionPane.showMessageDialog(null, "Item removido com sucesso!");
                }else{
                    JOptionPane.showMessageDialog(null, "Erro ao remover!");
                }
            }
        });

        btnAtualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = comboBox1.getSelectedIndex();
                String tipo = (String) comboBox1.getItemAt(index);
                Vector<String> lista = AcervoControler.listarAcervo(tipo);
                list.setListData(lista);
            }
        });
        btnEmprestar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = list.getSelectedIndex();
                String item = list.getModel().getElementAt(id);

                int posicaoAbertura = item.indexOf("[");
                int posicaoFechamento = item.indexOf("]");

                int idAc = Integer.parseInt(item.substring(posicaoAbertura + 1, posicaoFechamento));

                int status = AcervoControler.emprestar(idAc);
                if(status == AcervoControler.ITEM_EMPRESTADO) {
                    JOptionPane.showMessageDialog(null, "Item emprestado com sucesso!");
                }else if (status == AcervoControler.ITEM_NAO_ENCONTRADO){
                    JOptionPane.showMessageDialog(null, "Item não encontrado!");
                }else if (status == AcervoControler.ITEM_SEM_EXEMPLAR_DISPONIVEL){
                    JOptionPane.showMessageDialog(null, "Item sem exemplares disponiveis!");
                }
            }
        });
        btnListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = comboBox1.getSelectedIndex();
                String tipo = (String) comboBox1.getItemAt(index);
                Vector<String> lista = AcervoControler.listarAcervo(tipo);

                if (lista.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Não há itens cadastrados do tipo selecionado!");
                }

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

