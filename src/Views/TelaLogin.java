package Views;

import Controllers.AuthenticationController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Views.TelaMenu;
public class TelaLogin extends JDialog {
    public JTextField textUsuario;
    private JPasswordField passSenha;
    private JLabel lUsuario;
    private JLabel lSenha;
    private JButton btnOk;
    private JButton btnCancelar;
    private JPanel Tela;
    private JLabel lBiblioteca;

    private TelaLogin tl;

    public TelaLogin(JFrame parent)  {
        super(parent);

        setContentPane(Tela);
        setMinimumSize(new Dimension(700, 300));
        setModal(true);


        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = textUsuario.getText();
                String senha = passSenha.getText();
                if(AuthenticationController.login(usuario, senha) == true){
                    TelaMenu tm = new TelaMenu();
                    dispose();
                    tm.setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos");
                }
            }
        });
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

    }

}
