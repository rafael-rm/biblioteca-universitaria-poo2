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

    public void cadastro(){
        String titulo = textTitulo.getText();
        String autor = textFieldAutor.getText();
        String assunto = textAssunto.getText();
        String palavrasChaves = textFieldPc.getText();
        String cdu = fTextCdu.getText();
        String editora = textEditora.getText();
        String cidade = textCidade.getText();
        String ano = fTextFieldAno.getText();

        String tipo;

        switch (cbTipo.getSelectedIndex.){
            case 0:
                tipo = "Livro";

            case 1:
                tipo =  "Cartaz";

            case 2:
                tipo = "Relatorio";

            case 3:
                tipo = "Periodico";

            case 4:
                tipo = "Midia";

            case 5:
                tipo =  "Tcc";

            case 6:
                tipo = "Mapa";

            default:
                tipo = "Livro";
        }

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
    }

}
