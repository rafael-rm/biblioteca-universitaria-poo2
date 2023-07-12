package Views;

import Controllers.AcervoControler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    public TelaCadastro(){
        setContentPane(Cadastro);
        setMinimumSize(new Dimension(700, 500));


        btnCadastro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }



}
