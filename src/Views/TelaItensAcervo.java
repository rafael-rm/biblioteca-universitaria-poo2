package Views;

import Controllers.AcervoControler;
import Model.AcervoBase;
import Model.Utils;

import javax.swing.*;
import java.awt.*;

import java.util.List;
import java.util.Vector;


public class TelaItensAcervo extends JFrame {
    private JPanel Itens;
    private JLabel JItens;
    private JLabel JImg;
    private JList<String> list;



    public TelaItensAcervo() {

        setContentPane(Itens);
        setMinimumSize(new Dimension(700, 500));
        String tipo = "Acervo";
        Vector<String> lista = AcervoControler.listarAcervo(tipo);
        list.setListData(lista);
    }

}

