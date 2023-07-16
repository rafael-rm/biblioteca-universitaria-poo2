package Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class TelaMenu extends JFrame {
    private JPanel Menu;
    private JLabel lPng;
    private JLabel lBemVindo;
    private JComboBox comboBoxMenu;
    private JLabel JLabelOp;
    private JButton btnOk;
    private JButton btnVoltar;


    public TelaMenu (){

        setContentPane(Menu);
        setMinimumSize(new Dimension(400, 400));



        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (comboBoxMenu.getSelectedIndex()){
                    case 0:
                        TelaCadastro tc = new TelaCadastro();
                        dispose();
                        tc.setVisible(true);
                        break;
                    case 1:
                        TelaItensAcervo tm = new TelaItensAcervo();
                        dispose();
                        tm.setVisible(true);
                        break;
                    case 2:
                        TelaUpdate tup = new TelaUpdate();
                        dispose();
                        tup.setVisible(true);
                        break;
                    case 3:
                        break;


                }
            }
        });
        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaLogin tl = new TelaLogin(null);
                dispose();
                tl.setVisible(true);
            }
        });
    }


}
