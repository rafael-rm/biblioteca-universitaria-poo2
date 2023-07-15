package Views;

import Controllers.AcervoControler;
import Model.AcervoBase;
import Controllers.AcervoControler;
import Model.Utils;

import javax.swing.*;
import java.awt.*;

import java.util.List;


public class TelaItensAcervo extends JFrame {
    private JPanel Itens;
    private JLabel JItens;
    private JLabel JImg;
    private JList list;



    public TelaItensAcervo() {

        setContentPane(Itens);
        setMinimumSize(new Dimension(700, 500));

        List<AcervoBase> listAcervos = Utils.obterTodosDoBanco();
        AcervoBase[] arrayAcervos = listAcervos.toArray(new AcervoBase[0]);
        list.setListData(arrayAcervos);


    }

}

