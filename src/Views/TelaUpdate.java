package Views;

import Model.AcervoBase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class TelaUpdate extends JFrame{
    private JLabel JLTitulo;
    private JTextField textTitulo;
    private JLabel JLabelAssunto;
    private JTextField textAssunto;
    private JLabel JLabelPc;
    private JTextField textFieldPc;
    private JLabel JLabelCdu;
    private JFormattedTextField fTextCdu;
    private JTextField textFieldAutor;
    private JLabel JLabelAutor;
    private JLabel JLabelQtExemplares;
    private JTextField textQtExemplares;
    private JPanel Cadastro2;
    private JTextField textEditora;
    private JLabel JLabelAno;
    private JLabel JLabelEditora;
    private JTextField textCidade;
    private JFormattedTextField fTextFieldAno;
    private JLabel JLabelEdicao;
    private JTextField textEdicao;
    private JLabel JLabelQt;
    private JTextField textQt;
    private JLabel JLabelTam;
    private JTextField textTam;
    private JLabel JLabelCidade;
    private JLabel JLabelTipo;
    private JComboBox cbTipo;
    private JButton btnAtualizar;
    private JPanel Editar;
    private javax.swing.JLabel JLabel;
    private JLabel JLabelimg;
    private JLabel JLabelid;
    private JTextField textId;

    public void atualizar(){
        AcervoBase item = new AcervoBase();
        item.setTitulo(textTitulo.getText());
        item.setAutor(textFieldAutor.getText());
        item.setAssunto(textAssunto.getText());
        item.setPalavras_chave_string(textFieldPc.getText());
        item.setCdu(fTextCdu.getText());
        item.setEditora(textEditora.getText());
        item.setCidade(textCidade.getText());
        item.setAno(Integer.parseInt(fTextFieldAno.getText()));
        item.setEdicao(Integer.parseInt(textEdicao.getText()));
        item.setQtd_exemplares(Integer.parseInt(textQtExemplares.getText()));
        item.setTipoAcervo(Objects.requireNonNull(cbTipo.getSelectedItem()).toString());
        item.setId(Integer.parseInt(textId.getText()));
        item.atualizarNoBanco(item, item.getId());
    }

    public TelaUpdate (){
        setContentPane(Editar);
        setMinimumSize(new Dimension(700, 500));
        btnAtualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizar();
            }
        });
    }
}
