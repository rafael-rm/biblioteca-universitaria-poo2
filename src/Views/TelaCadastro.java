package Views;

import Controllers.AcervoControler;
import Model.AcervoBase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class TelaCadastro extends JFrame{
    private JPanel Cadastro;
    public JTextField textTitulo;
    private JLabel JLTitulo;
    private JTextField textFieldAutor;
    private JLabel JLabelAutor;
    public JTextField textAssunto;
    private JTextField textFieldPc;
    private JLabel JLabelAssunto;
    private JLabel JLabelPc;
    public JFormattedTextField fTextCdu;
    private JLabel JLabelCdu;
    private JPanel Cadastro2;
    private JLabel JLabelEditora;
    public JTextField textEditora;
    private javax.swing.JLabel JLabelCidade;
    public JTextField textCidade;
    private JLabel JLabelAno;
    private JFormattedTextField fTextFieldAno;
    private JLabel JLabelCad;
    private JLabel JLabelimg;
    public JComboBox cbTipo;
    private JLabel JLabelTipo;
    private JButton btnCadastro;
    private JLabel JLabelEdicao;
    private JTextField textEdicao;
    private JLabel JLabelQt;
    private JTextField textQt;
    private JLabel JLabelTam;
    private JTextField textTam;
    private JLabel JLabelQtExemplares;
    private JTextField textQtExemplares;
    private JButton btnVoltar;

    public void cadastro(){
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
        item.setTam_pag(Integer.parseInt(textTam.getText()));
        item.setNum_pag(Integer.parseInt(textQt.getText()));

        boolean status = AcervoControler.cadastrarAcervo(item);

        if (status)
            JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");
        else
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar!");
    }

    public TelaCadastro() {
        setContentPane(Cadastro);
        setMinimumSize(new Dimension(700, 500));
        btnCadastro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastro();
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
